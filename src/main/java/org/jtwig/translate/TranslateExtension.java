package org.jtwig.translate;

import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.extension.Extension;
import org.jtwig.translate.configuration.TranslateConfiguration;
import org.jtwig.translate.function.TranslateFunction;
import org.jtwig.translate.function.converter.StringToLocaleConverter;
import org.jtwig.translate.parser.TranslateAddonParserProvider;

import java.util.Collections;

public class TranslateExtension implements Extension {
    private final TranslateConfiguration configuration;

    public TranslateExtension(TranslateConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(EnvironmentConfigurationBuilder environmentConfigurationBuilder) {
        configuration.registerParameters(environmentConfigurationBuilder);
        environmentConfigurationBuilder
                .parser()
                    .withAddonParserProvider(new TranslateAddonParserProvider())
                    .and()
                .functions()
                    .withBeans(Collections.<Object>singleton(new TranslateFunction()))
                .and()
                .value().withConverter(new StringToLocaleConverter())
        ;
    }
}