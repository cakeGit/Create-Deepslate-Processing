package com.cak.deepslate_processing;

public class DPLang {
    
    public static void register() {
        add("itemGroup.deepslate_processing", "Create: Deepslate Processing");
    }
    
    public static void add(String key, String value) {
        DeepslateProcessingCommon.REGISTRATE.addRawLang(key, value);
    }
    
}
