package org.lxt.xiang.knife;

import org.lxt.xiang.library.GET;
import org.lxt.xiang.library.PUT;

/**
 * It is never too late to learn.
 */
public interface PrefsApi {

    @GET
    int getId();

    @GET
    String getName();

    @GET(key = "Version")
    int getVersion();

    @PUT
    void saveId(int id);

    @PUT
    void saveName(String name);

    @PUT(key = "Version")
    void saveVersion(int version);


}
