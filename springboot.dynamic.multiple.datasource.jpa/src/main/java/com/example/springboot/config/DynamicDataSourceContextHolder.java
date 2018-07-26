package com.example.springboot.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> dataSourceContextHolder = new ThreadLocal<>();
    public static Set<String> dataSourcePrefixs = new HashSet<>();

    public static void setDataSource(String dataSourceMark) {
        dataSourceContextHolder.set(dataSourceMark);
    }

    public static String getDataSource() {
        return dataSourceContextHolder.get();
    }

    public static void clearDataSource() {
        dataSourceContextHolder.remove();
    }

    public static boolean isContainsDataSource(String dataSourcePrefix) {
        return dataSourcePrefixs.contains(dataSourcePrefix);
    }

}
