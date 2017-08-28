package com.artisan.commonUtils.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 

 * @ClassName: SendEmailUtil

 * @Description: 发送邮件工具类

 * @author: Mr.Yang

 * @date: 2017年8月28日 下午4:50:35
 */
public class SendEmailUtil {
	/**
	 * 
	 * 
	 * @Title: sendEmail
	 * 
	 * @Description: 发送邮件工具类方法
	 * 
	 * @param sendEmail
	 *            发件人地址
	 * @param sendEmailPwd
	 *            授权码代替密码（更安全） 授权码的获取：进入个人邮箱，点击设置–>账户， SMTP服务选项 默认情况下这个选项是不开启的。
	 *            点击开启腾讯会进行身份验证，身份验证通过以后，会收到一个用于使用SMTP的16位口令，
	 *            验证身份的过程中把收到的口令保存下来，因为后面要使用SMTP功能必须要用到这个口令。
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @param toEmilAddress
	 *            收件人地址
	 * @throws Exception
	 * 
	 * @return: void
	 */
	public static void sendEmail(String sendEmail, String sendEmailPwd, String title, String content,
			String[] toEmilAddress) throws Exception {
		Properties props = new Properties();
		// 开启debug调试，以便在控制台查看
		props.setProperty("mail.debug", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		Session session = Session.getInstance(props);
		Message msg = new MimeMessage(session);
		// 发送的邮箱地址
		msg.setFrom(new InternetAddress(sendEmail));
		// 设置标题
		msg.setSubject(title);
		// 设置内容
		msg.setContent(content, "text/html;charset=gbk;");
		Transport transport = session.getTransport();
		// 设置服务器以及账号和密码
		// 这里端口改成465
		transport.connect("smtp.qq.com", sendEmail, sendEmailPwd);
		// 发送到的邮箱地址
		transport.sendMessage(msg, getAddress(toEmilAddress));
		
		if(transport!=null){
             try {
                 transport.close();
             } catch (MessagingException e) {
                 e.printStackTrace();
             }
         }
	}
	
	 /**
     * 
    
     * @Title: getAddress
    
     * @Description: 遍历收件人信息
    
     * @param emilAddress
     * @return
     * @throws Exception
    
     * @return: Address[]
     */
	private static Address[] getAddress(String[] emilAddress) throws Exception {
		Address[] address = new Address[emilAddress.length];
		for (int i = 0; i < address.length; i++) {
			address[i] = new InternetAddress(emilAddress[i]);
		}
		return address;
	}

	/**
	 * 
	 * 
	 * @Title: main
	 * 
	 * @Description: 测试
	 * 
	 * @param args
	 * @throws Exception
	 * 
	 * @return: void
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * * @param sendEmail 发件人地址
		 * 
		 * @param sendEmailPwd
		 *            授权码代替密码（更安全） 
		 * @param title
		 *            邮件标题
		 * @param content
		 *            邮件内容
		 * @param toEmilAddress
		 *            收件人地址
		 */
		SendEmailUtil.sendEmail("815150141@qq.com", "zdwhvrawiidsbffi", "testEmail", "testcontent",
				new String[] { "yswcomeon@gmail.com", "815150141@qq.com" });
	}
}

/**
 * 在发邮件过程中有的人会发送不成功，出现如下错误: 
 * javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure
 * 
 * 这个是jdk导致的，jdk里面有一个jce的包，安全性机制导致的访问https会报错，官网上有替代的jar包，换掉就好了
 * 
 * 对应包的下载地址：
 * http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
 * 
 * 下载好后，直接替换掉本地JDK中的对应的两的包就好了。
 * 
 */
