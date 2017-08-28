package com.artisan.commonUtils.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 

 * @ClassName: SendEmailWithAttachment

 * @Description: Email发送附件 [如果需要发送附件，就需要用到Multipart对象]

 * @author: Mr.Yang

 * @date: 2017年8月28日 下午4:49:35
 */
public class SendEmailWithAttachment {
	private MimeMessage message;
    private Session session;
    private Transport transport;

    private String mailHost = "";
    private String sender_username = "";
    private String sender_password = "";

    private Properties properties = new Properties();
    
    /*
     * 初始化方法
     */
    public SendEmailWithAttachment(boolean debug) {
        InputStream in = SendEmailWithAttachment.class.getResourceAsStream("SMTPMailServer.properties");
        try {
            properties.load(in);
            this.mailHost = properties.getProperty("mail.smtp.host");
            this.sender_username = properties.getProperty("mail.sender.username");
            this.sender_password = properties.getProperty("mail.sender.password");
            
            // 开启SSL加密，否则会失败
    		MailSSLSocketFactory sf = new MailSSLSocketFactory();
    		sf.setTrustAllHosts(true);
    		properties.put("mail.smtp.ssl.enable", "true");
    		properties.put("mail.smtp.ssl.socketFactory", sf);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

        session = Session.getInstance(properties);
        session.setDebug(debug);// 开启后有调试信息
        message = new MimeMessage(session);
    }
    
    /**
     * 发送邮件
     * 
     * @param subject
     *            邮件主题
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     * @param attachment
     *            附件
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String[] receiveUsers, File attachment) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                
                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
                
                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
            
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, getAddress(receiveUsers));

            System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
    
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
    
    
    public static void main(String[] args) {
    	SendEmailWithAttachment se = new SendEmailWithAttachment(true);
        File attached = new File("D:\\workspace\\ws-java-base\\commonUtils\\pom.xml");
        se.doSendHtmlEmail("邮件主题带有附件", "邮件内容", new String[]{"yswcomeon@gmail.com"}, attached);
    }
    
}
