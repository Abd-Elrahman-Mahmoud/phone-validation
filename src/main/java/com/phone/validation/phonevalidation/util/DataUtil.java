package com.phone.validation.phonevalidation.util;

import java.util.Collection;
import java.util.stream.Stream;

public class DataUtil {

    public static <T> Stream<T> safeStream(Collection<T> collection) {

        return collection == null ? Stream.empty() : collection.stream();

    }

}