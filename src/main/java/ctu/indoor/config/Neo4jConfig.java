/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


/**
 *
 * @author ADMIN
 */
@ApplicationScoped
public class Neo4jConfig {

    @ConfigProperty(name = "quarkus.neo4j.uri")
    String neo4jUri;

    @ConfigProperty(name = "quarkus.neo4j.authentication.username")
    String neo4jUsername;

    @ConfigProperty(name = "quarkus.neo4j.authentication.password")
    String neo4jPassword;

    @Produces
    @ApplicationScoped
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration.Builder()
                .uri(neo4jUri) // ✅ Change this if needed
                .credentials(neo4jUsername, neo4jPassword) // ✅ Set your credentials
                .build();

        return new SessionFactory(configuration, "ctu.indoor.model"); // ✅ Your package for entity scanning
    }

    @Produces
    public Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }
}