package dev.theycreeper.simplehome.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {
    private static final List<String> OVERRIDE_ARGS = List.of("-override", "-o");

    public static List<String> getSanitizedArgsList(String[] args){
        List<String> argsList =  new ArrayList<>(List.of(args));
        argsList.removeIf(OVERRIDE_ARGS::contains);
        return argsList;
    }
}
