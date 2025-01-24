package com.pet.pet.core.config;

import cn.hutool.extra.mail.MailAccount;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("mail.smtp")
public class MailConfig {
    private String host;
    private int port;
    private String mailFrom;
    private String password;
    private boolean ssl = true;
    private boolean auth = true;

    @Bean
    public MailAccount getMailAccount() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(host);
        mailAccount.setPort(port);
        mailAccount.setFrom(mailFrom);
        mailAccount.setUser(mailFrom);
        mailAccount.setPass(password);
        mailAccount.setAuth(auth);
        mailAccount.setSslEnable(true);
        return mailAccount;
    }
}