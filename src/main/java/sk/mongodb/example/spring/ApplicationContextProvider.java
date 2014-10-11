package sk.mongodb.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Silvia on 8. 10. 2014.
 */
public class ApplicationContextProvider implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    private static ApplicationContextProvider applicationContextProvider;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    private ApplicationContextProvider() {

    }

    public static ApplicationContextProvider getInstance() {
        if (applicationContextProvider == null) {
            applicationContextProvider = new ApplicationContextProvider();
        }
        return applicationContextProvider;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        synchronized (ApplicationContextProvider.class) {
            ApplicationContextProvider.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext get() {
        synchronized (ApplicationContextProvider.class) {
            return ApplicationContextProvider.applicationContext;
        }
    }

    @Override
    public void destroy() throws Exception {
        synchronized (ApplicationContextProvider.class) {
            applicationContext = null;
        }
    }
}
