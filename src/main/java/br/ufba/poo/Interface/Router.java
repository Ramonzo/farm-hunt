package br.ufba.poo.Interface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.swing.*;

public class Router {
    private static Collection<Route> routes;
    private Route currentRoute;

    public Router() {
        routes = new ArrayList<Route>();
    }

    public void createRoute(String p, JPanel s, RouteHandler c) {
        Route newRoute = new Route(p, s, c);
        if (!routes.contains(newRoute))
            routes.add(newRoute);
    }

    public void goTo(String p) {
        try {
            Optional<Route> goToRoute = routes.stream().filter(r -> r.getPath().equals(p)).findFirst();

            if (goToRoute != null) {
                currentRoute = goToRoute.get();
                currentRoute.execute();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Route getRoute() {
        return new Route(currentRoute);
    }
}