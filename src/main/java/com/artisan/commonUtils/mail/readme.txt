1.  SendEmailUtil.java 工具类 可单独使用，
	但是发件人只能是QQ邮箱（因为smtp的服务器是smtp.qq.com）
	使用之前需要先在QQ邮箱账户中通过设置获取sendEmailPwd（授权码）
	发件人邮箱和授权码必须要对应，否则会鉴权失败  javax.mail.AuthenticationFailedException
	
	
2. SendMailUtil2.java 将配置信息提取到了properties文件中，其余同1一样