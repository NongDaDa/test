package zoo;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Date;

public class Test {



    public static void main(String[] args) throws Exception {
        send2();
    }

    /**
     * java发送邮件测试
     */


    // 发件人的邮箱地址和密码

    public static String sendEmailAccount = "1067050494@qq.com";

    //如果有授权码，此处填写授权码

    public static String sendEmailPassword = "1+1=2nhjh424589";

    // 发件人邮箱的 SMTP 服务器地址, 可以登录web邮箱查询

    public static String sendEmailSMTPHost = "smtp.qq.com";

    // 收件人邮箱地址

    public static String receiveMailAccount = "1067050494@qqq.com";

    public static void send() {

        // 参数配置

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");

        props.setProperty("mail.smtp.host", sendEmailSMTPHost);

        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.setProperty("mail.smtp.port", "465");

        props.setProperty("mail.smtp.socketFactory.port", "465");

        // 根据配置创建会话对象, 用于和邮件服务器交互

        Session session = Session.getDefaultInstance(props);

        session.setDebug(true);   // 设置为debug模式, 可以查看详细的发送 log

        Transport transport = null;

        // 创建一封邮件
        try {

            Message message = createMimeMessage(session, sendEmailAccount, receiveMailAccount);

            // 根据 Session 获取邮件传输对象

            transport = session.getTransport();

            // 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则会报错

            transport.connect(sendEmailAccount, sendEmailPassword);

            // 发送邮件

            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {

        } finally {

            // 关闭连接
            if (transport != null) {
                try {
                    transport.close();
                } catch (Exception e) {

                } finally {

                }
            }
        }


    }

    /**
     * 创建一封简单邮件
     */

    private static Message createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sendMail));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

        // 设置邮件标题

        message.setSubject("发送邮件测试");

        // 设置邮件正文

        message.setText("这是测试内容，请忽略此内容详情");

        message.setSentDate(new Date());

        //保存设置

        message.saveChanges();

        return message;

    }

    public static void send2() throws Exception {

        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("主题");
        StringBuilder builder = new StringBuilder();
        builder.append("胡子&小猿的博客:");
        builder.append("url = " + "http://www.cnblogs.com/hzxy-blog/");
        msg.setText(builder.toString());
       // msg.setFrom(new InternetAddress("**发送人的邮箱地址**"));
        msg.setFrom(new InternetAddress("1067050494@qq.com"));

        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "1067050494@qq.com", "niwvmfyyltdmbfhg");

        transport.sendMessage(msg, new Address[] { new InternetAddress("1067050494@qq.com") });
        transport.close();
    }

}