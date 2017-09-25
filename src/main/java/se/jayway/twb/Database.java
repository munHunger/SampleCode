package se.jayway.twb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Database
{


    private static SessionFactory sessionFactory;

    private static void init()
    {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        MetadataSources mds = new MetadataSources(registry);
        Metadata md = mds.buildMetadata();
        sessionFactory = md.buildSessionFactory();
        //Make sure that the service registry is destroyed on shutdown by adding a shutdown hook to the runtime
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                                                        {
                                                            StandardServiceRegistryBuilder.destroy(registry);
                                                        }));
    }

    public static void saveObject(Object o) throws Exception
    {
        if(sessionFactory == null)
            init();
        try (Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        }
    }
}
