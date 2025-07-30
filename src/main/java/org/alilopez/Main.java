package org.alilopez;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.alilopez.di.AppModule;

public class Main {
    public static void main(String[] args) {

        // 1. Crear y configurar la aplicación Javalin
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(CorsPluginConfig.CorsRule::anyHost);
            });
        }).start(7000);

        // 2. Ruta básica de prueba
        app.get("/", ctx -> ctx.result("API Javalin 2"));

        // 3. Registrar todas las rutas de la aplicación
        AppModule.initUser().register(app);        // /users, /users/{id}
        AppModule.initProducts().register(app);


    }
}