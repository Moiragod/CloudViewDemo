package com.moiragod.http.cache;

import android.util.LruCache;

/**
 * Created by tdu011 on 11/9/17.
 */

public class TimedMemoryCache {

    public static Long LONG_CACHE_TIME = 1000*3600l; //1 hrs
    public static Long FIVE_MIN_CACHE_TIME = 1000 * 60 * 5l; //5 min
    public static Long ONE_MIN_CACHE_TIME = 1000 * 60l; //1 min
    public static Long HALF_MIN_CACHE_TIME = 1000 * 30l; //haft min

    private String SEPARATOR = ":::";

    private LruCache<Short ,String> mMemoryCache ;

    private static  TimedMemoryCache memoryCacheUtils = new TimedMemoryCache();

    public static TimedMemoryCache getInstance(){
        return memoryCacheUtils;
    }

    private TimedMemoryCache(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        mMemoryCache = new LruCache<>((int) maxMemory);
    }

    public void setCache(short URL, String info){
        long nowTimeMillis = System.currentTimeMillis();
        String content = nowTimeMillis + SEPARATOR + info;
        mMemoryCache.put(URL,content);
    }

    public String getCache(short URL){
        long nowTimeMillis = System.currentTimeMillis();
        String content = mMemoryCache.get(URL);
        if(content!=null){
            String saveTimeMillisString = content.split(SEPARATOR)[0];
            String info = content.split(SEPARATOR)[1];
            long saveTimeMillis = Long.parseLong(saveTimeMillisString);
            if((nowTimeMillis - saveTimeMillis) > LONG_CACHE_TIME){
                mMemoryCache.remove(URL);
                return null;
            }else{
                return info;
            }
        }else{
            return null;
        }
    }

    public String getCache(Short URL, long time){
        long nowTimeMillis = System.currentTimeMillis();
        String content = mMemoryCache.get(URL);
        if(content!=null){
            String saveTimeMillisString = content.split(SEPARATOR)[0];
            String info = content.split(SEPARATOR)[1];
            long saveTimeMillis = Long.parseLong(saveTimeMillisString);
            if((nowTimeMillis - saveTimeMillis) > time){
                mMemoryCache.remove(URL);
                return null;
            }else{
                return info;
            }
        }else{
            return null;
        }
    }

    public void removeCache(Short URL){
        mMemoryCache.remove(URL);
    }

    public void removeAllCache(){
        mMemoryCache.evictAll();
    }


}
