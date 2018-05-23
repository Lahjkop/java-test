package com.etnetera.hr.rest;

//TODO
public enum HypeLevel {
    INOVATION_TRIGGER("COOL", 1),
    PEAK_OF_EXPECTATION("SUPER COOL", 2),
    SLOPE_OF_TROUGH("WTF", 4),
    CLIBING_THE_SLOPE("ONE MORE CHANCE", 5),
    PLATEAU("NEVIM", 6),
    BORN_TO_WIN("SUCCESSFUL", 7),
    BORN_TO_LOSE("", 8),
    DEPRICATED("ALREADY DEATH",9);


    private final String code;
    private final int coolnesLevel;

    HypeLevel(String code, int coolnesLevel){
        this.code = code;
        this.coolnesLevel = coolnesLevel;
    }


}
