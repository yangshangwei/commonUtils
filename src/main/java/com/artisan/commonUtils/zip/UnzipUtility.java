package com.artisan.commonUtils.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 
 * 
 * @ClassName: UnzipUtility
 * 
 * @Description: The java.util.zip package provides the following classes for
 *               extracting files and directories from a ZIP archive:
 * 
 *               A:----> ZipInputStream: this is the main class which can be
 *               used for reading zip file and extracting files and directories
 *               (entries) within the archive. Here are some important usages of
 *               this class:
 * 
 *               1.read a zip via its constructor
 *               ZipInputStream(FileInputStream)
 * 
 *               2.read entries of files and directories via method
 *               getNextEntry()
 * 
 *               3.read binary data of current entry via method read(byte)
 * 
 *               4.close current entry via method closeEntry()
 * 
 *               5.close the zip file via method close()
 * 
 * 
 * 
 *               B:----> ZipEntry: this class represents an entry in the zip
 *               file. Each file or directory is represented as a ZipEntry
 *               object. Its method getName() returns a String which represents
 *               path of the file/directory.
 * 
 *               The path is in the following form:
 *               folder_1/subfolder_1/subfolder_2/…/subfolder_n/file.ext
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年9月7日 下午8:10:06
 * 
 * 
 * @comments The UnzipUtility class has a public method for extracting files and
 *           directories from a zip archive:
 * 
 *           unzip(String zipFilePath, String destDirectory):
 * 
 *           extracts content of a zip file specified by zipFilePath to a
 *           directory specified by destDirectory.
 * 
 */
public class UnzipUtility {
	/**
	 * Size of the buffer to read/write data
	 */
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Extracts a zip file specified by the zipFilePath to a directory specified
	 * by destDirectory (will be created if does not exists)
	 * 
	 * @param zipFilePath
	 * @param destDirectory
	 * @throws IOException
	 */
	public static void unzip(String zipFilePath, String destDirectory) throws IOException {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	/**
	 * Extracts a zip entry (file entry)
	 * 
	 * @param zipIn
	 * @param filePath
	 * @throws IOException
	 */
	public static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	public static void main(String[] args) {
		String zipFilePath = "D:/test.zip";
		String destDirectory = "H:/";
		try {
			UnzipUtility.unzip(zipFilePath, destDirectory);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
