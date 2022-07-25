package com.zozidalom.lpsolutionstask.configurations.freemaker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.AbstractMap;

@org.springframework.context.annotation.Configuration
public class StringTemplateLoaderConfiguration {
    @Bean
    public AbstractMap.SimpleImmutableEntry<StringTemplateLoader, Configuration> stringTemplateLoader() {
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        Configuration configuration = new Configuration();
        configuration.setTemplateLoader(stringTemplateLoader);
        return new AbstractMap.SimpleImmutableEntry<>(stringTemplateLoader, configuration);
    }
}
