package com.zozidalom.lpsolutionstask.controllers;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static j2html.TagCreator.*;

@Controller
public class TemplateController {
    private final AbstractMap.SimpleImmutableEntry<StringTemplateLoader, Configuration> stringTemplateLoaderConfigurationSimpleImmutableEntry;

    public TemplateController(AbstractMap.SimpleImmutableEntry<StringTemplateLoader, Configuration> stringTemplateLoaderConfigurationSimpleImmutableEntry) {
        this.stringTemplateLoaderConfigurationSimpleImmutableEntry = stringTemplateLoaderConfigurationSimpleImmutableEntry;
    }

    /***
     * An endpoint to render the html template.
     * @param name The name to use in the template.
     * @param email The email to use in the template.
     * @param repositoryUrl The repository url to use in the template.
     * @return The parsed html page.
     */
    @SneakyThrows
    @GetMapping(value = "/template/show")
    @ResponseBody
    public String showTemplate(@RequestParam(value = "name", defaultValue = "Nagy Zoltán") String name, @RequestParam(value = "email", defaultValue = "zozo.nagy02@gmail.com") String email,
                              @RequestParam(value = "repository_url", defaultValue = "https://github.com/zozidalom/LPSolutionsTask") String repositoryUrl) {
        String html = html(
                head(
                        title("${name} - Teszt Feladat"),
                        body(
                                h1("Teszt Feladat"),
                                p(
                                        a("Megoldás").withHref("${repository_url}")
                                ),
                                p("A feladat elkészítőjének adatai"),
                                table(
                                        tr(
                                                td("Név"),
                                                td("${name}")
                                        ),
                                        tr(
                                                td("Elérhetőség"),
                                                td("${email}")
                                        )
                                ).attr("border", "1px solid black"),
                                a("L&P Solutions").withHref("http://lpsolutions.hu")
                        ))).render();

        stringTemplateLoaderConfigurationSimpleImmutableEntry.getKey().putTemplate("template", html);
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("repository_url", repositoryUrl);
        StringWriter stringWriter = new StringWriter();
        stringTemplateLoaderConfigurationSimpleImmutableEntry.getValue().getTemplate("template").process(params, stringWriter);
        return stringWriter.toString();
    }
}
