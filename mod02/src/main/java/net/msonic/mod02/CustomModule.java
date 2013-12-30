package net.msonic.mod02;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.msonic.framework.LoginProxy;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class CustomModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoginProxy.class).in(Singleton.class);
    }

}
