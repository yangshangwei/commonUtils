1.  SendEmailUtil.java 工具类 可单独使用，
	但是发件人只能是QQ邮箱（因为smtp的服务器是smtp.qq.com）
	使用之前需要先在QQ邮箱账户中通过设置获取sendEmailPwd（授权码）
	发件人邮箱和授权码必须要对应，否则会鉴权失败  javax.mail.AuthenticationFailedException
	
	
2. SendMailUtil2.java 将配置信息提取到了properties文件中，其余同1一样

实现java发送邮件的过程大体有以下几个步骤：
a.准备一个properties文件，该文件中存放SMTP服务器地址等参数。(直接设置也可以)
b.利用properties创建一个Session对象
c.利用Session创建Message对象，然后设置邮件主题和正文
d.利用Transport对象发送邮件

依赖包的maven地址：
	<!-- https://mvnrepository.com/artifact/com.sun.mail/javax.mail -->
		<dependency>
			<groupId>com.sun.mail</groupId>
			<artifactId>javax.mail</artifactId>
			<version>${javax.mail.version}</version>
		</dependency>

