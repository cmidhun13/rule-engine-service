package com.dxunited.ruleengine.service.controller;


import com.dxunited.ruleengine.service.datafetcher.RuleDataFetcher;
import com.dxunited.ruleengine.service.datafetcher.RuleDetailsFetcher;
import com.dxunited.ruleengine.service.datafetcher.RuleProjectDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.*;

@Controller
public abstract class CustomerRuleAbstractController {

    @Value("classpath:ruleengine.graphqls")
    private Resource schemaResource;
    private RuleProjectDataFetcher dataFetcher;

    @Value("classpath:runrule.graphqls")
    private Resource schemaResourceRule;
    private RuleDataFetcher ruleDataFetcher;

    @Value("classpath:ruleDetails.graphqls")
    private Resource schemaResourceRuleDetails;
    private RuleDetailsFetcher ruleDetailsDataFetcher;

    private GraphQL loadDataFetcher(DataFetcher dataFetcher) throws IOException {
        this. dataFetcher=(RuleProjectDataFetcher)dataFetcher;
        InputStream inf= schemaResource.getInputStream();
        Reader reader = new InputStreamReader(inf);
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(reader);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        return graphQL;
    }
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("customer", dataFetcher))
                .build();
    }

    public GraphQL buildResponse(DataFetcher dataFetcher) throws IOException {
        GraphQL loadSchema = loadDataFetcher(dataFetcher);
        return loadSchema;
    }

    private GraphQL loadRuleDetailsFetcher(DataFetcher dataFetcher) throws IOException {
        this. ruleDetailsDataFetcher=(RuleDetailsFetcher)dataFetcher;

        //Get the graphql file
//        File file = schemaResource.getFile();
        InputStream inf= schemaResourceRuleDetails.getInputStream();
        Reader reader = new InputStreamReader(inf);
        //Parse SchemaFile
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(reader);
        RuntimeWiring runtimeWiring = buildRuleDetailsRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        return graphQL;
    }
    public GraphQL buildRuleDetailsResponse(DataFetcher dataFetcher) throws IOException {
        GraphQL loadSchema = loadRuleDetailsFetcher(dataFetcher);
        return loadSchema;
    }
    private RuntimeWiring buildRuleDetailsRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Queries", typeWiring -> typeWiring
                        .dataFetcher("ruleName", ruleDetailsDataFetcher))
                .build();
    }

    private GraphQL loadRuleDataFetcher(DataFetcher dataFetcher) throws IOException {
        this. ruleDataFetcher=(RuleDataFetcher)dataFetcher;
        InputStream inf= schemaResourceRule.getInputStream();
        Reader reader = new InputStreamReader(inf);
        //Parse SchemaFile
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(reader);
        RuntimeWiring runtimeWiring = buildRuleRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        return graphQL;
    }

    private RuntimeWiring buildRuleRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Queries", typeWiring -> typeWiring
                        .dataFetcher("ruleName", ruleDataFetcher))
                .build();
    }

    public GraphQL buildRuleResponse(DataFetcher dataFetcher) throws IOException {
        GraphQL loadSchema = loadRuleDataFetcher(dataFetcher);
        return loadSchema;
    }
}
