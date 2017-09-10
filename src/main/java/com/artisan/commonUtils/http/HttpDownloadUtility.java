package com.artisan.commonUtils.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * 
 * @ClassName: HttpDownloadUtility
 * 
 * @Description: In Java, we can use the classes URL and HttpURLConnection in
 *               the package java.net to programmatically download a file from a
 *               given URL by following these steps:
 * 
 *               1.Create a URL object for a given URL. The URL can be either:
 * 
 *               1(1) A direct link which contains the real file name at the
 *               end, for example:
 *               http://www-us.apache.org/dist/maven/maven-3/3.5.0/binaries/
 *               apache-maven-3.5.0-bin.tar.gz
 * 
 *               1(2) An indirect link which does not contain the real file
 *               name, for example: http://myserver.com/download?id=1234
 * 
 *               2.Open connection on the URL object – which would return an
 *               HttpURLConnection object if the URL is an HTTP URL.
 * 
 *               3.Open the input stream of the opened connection.
 * 
 *               4.Create an output stream to save file to disk.
 * 
 *               5.Repeatedly read array of bytes from the input stream and
 *               write them to the output stream, until the input stream is
 *               empty.
 * 
 *               6.Close the input stream, the output stream and the connection.
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年9月9日 下午11:49:50
 */
public class HttpDownloadUtility {
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Downloads a file from a URL
	 * 
	 * @param fileURL
	 *            HTTP URL of the file to be downloaded
	 * @param saveDir
	 *            path of the directory to save the file
	 * @throws IOException
	 */
	public static void downloadFile(String fileURL, String saveDir) throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			}

			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName;

			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			// close Stream
			outputStream.close();
			inputStream.close();

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}

	public static void main(String[] args) {

		String fileURL = "http://www-us.apache.org/dist/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz";
		String saveDir = "H:/";
		try {
			HttpDownloadUtility.downloadFile(fileURL, saveDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
