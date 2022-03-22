package com.example.iteratiatask.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.service.CurrencyASPParser;
import org.springframework.stereotype.Component;

@Component
public class DateQueryResolver implements GraphQLQueryResolver {

    private CurrencyASPParser parser;

    public DateQueryResolver(CurrencyASPParser parser) {
        this.parser = parser;
    }

    public String getDate() {
        return parser.getDate();
    }
}
