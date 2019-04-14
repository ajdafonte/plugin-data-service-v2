package com.npaw.techtest.plugindataservice;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.npaw.techtest.plugindataservice.config.AppConfig;


public class PluginDataServiceStartup
{
    private static final Logger LOG = LoggerFactory.getLogger(PluginDataServiceStartup.class);

    public static void main(final String[] args)
    {
        final int maxThreads = 250;
        final QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads);
        final Server server = new Server(threadPool);

        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(80);
        server.setConnectors(new Connector[] {connector});

        final ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath("/");
        final ServletContainer jersey = new ServletContainer(ResourceConfig.forApplicationClass(AppConfig.class));
        final ServletHolder servletHolder = new ServletHolder(jersey);
        servletContextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(servletContextHandler);

        LOG.info("Starting PluginDataService application...");

        try
        {
            server.start();
            server.join();
        }
        catch (final Exception ex)
        {
            LOG.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        }

        finally
        {
            server.destroy();
        }
    }
}
