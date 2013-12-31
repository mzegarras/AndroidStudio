package net.msonic.mod03;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by manuelzegarra on 31/12/13.
 */
public class CustomModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Usuario.class).in(Singleton.class);
    }

}

