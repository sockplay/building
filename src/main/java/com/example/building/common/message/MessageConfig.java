package com.example.building.common.message;

import java.util.Locale;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * MessageConfig
 */
@Configuration
public class MessageConfig {

    public final static String MESSAGE_LOG_SOURCE = "MESSAGE_LOG_SOURCE";

    public final static String MESSAGE_FRONT_SOURCE = "MESSAGE_FRONT_SOURCE";

    @Bean(MESSAGE_LOG_SOURCE)
    @ConditionalOnResource(resources = {"classpath:i18n/messages.properties"})
    public ReloadableResourceBundleMessageSource messageSource() {
        Locale.setDefault(Locale.JAPANESE);
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean(MESSAGE_FRONT_SOURCE)
    @ConditionalOnResource(resources = {"classpath:i18n/messages_front.properties"})
    public ReloadableResourceBundleMessageSource messageFrontSource() {
        Locale.setDefault(Locale.JAPANESE);
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages_front");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
