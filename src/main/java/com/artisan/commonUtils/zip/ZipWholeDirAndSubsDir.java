package com.artisan.commonUtils.zip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * 
 * @ClassName: ZipWholeDirAndSubsDir
 * 
 * @Description: Using the walk file tree feature of Java NIO,
 * 
 *               you can write a program that compresses a whole directory
 *               (including sub files and sub directories) with ease
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年9月7日 下午8:36:18
 */
public class ZipWholeDirAndSubsDir extends SimpleFileVisitor<Path> {

	private static ZipOutputStream zos;

	private Path sourceDir;

	public ZipWholeDirAndSubsDir(Path sourceDir) {
		this.sourceDir = sourceDir;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

		try {
			Path targetFile = sourceDir.relativize(file);

			zos.putNextEntry(new ZipEntry(targetFile.toString()));

			byte[] bytes = Files.readAllBytes(file);
			zos.write(bytes, 0, bytes.length);
			zos.closeEntry();

		} catch (IOException ex) {
			System.err.println(ex);
		}

		return FileVisitResult.CONTINUE;
	}

	public static void main(String[] args) {
		String dirPath = "H:/Sessions";
		Path sourceDir = Paths.get(dirPath);

		try {
			String zipFileName = dirPath.concat(".zip");
			zos = new ZipOutputStream(new FileOutputStream(zipFileName));

			Files.walkFileTree(sourceDir, new ZipWholeDirAndSubsDir(sourceDir));

			zos.close();
		} catch (IOException ex) {
			System.err.println("I/O Error: " + ex);
		}
	}

}
