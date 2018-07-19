package com.example.sbs.aischoolparentapp.Config;


public class ConstValue {

    public static String BASE_URL = "http://beis.xyz/aischool";
    public static String LOGIN_URL = BASE_URL + "/index.php/Api/parent_login";
    public static String GET_STEM = BASE_URL + "/index.php/Api/get_badge";
    public static String Badges_EVENT = "http://beis.xyz/aischool//uploads/badges/";
    public static String GETECO_POINT = BASE_URL + "/index.php/Api/get_eca_points";
    public static String GET_ACADEMIC_POINT = BASE_URL + "/index.php/Api/get_academic_point";
    public static String GET_BEHAVIOUR_POINT = BASE_URL + "/index.php/Api/get_behaviour_totalpoint";
    public static String GROWTH_URL = BASE_URL + "/index.php/api/get_student_growth";
    public static String FCM_REGISTER_URL = BASE_URL + "/index.php/api/register_fcm";
    public static String EVENT_DETAILS = BASE_URL + "/index.php/Api/get_news_event";
    public static String EVENT_URL = BASE_URL + "/index.php/api/get_school_event";
    public static String IMAGE_BASE_URL = "http://beis.xyz/aischool/uploads/eventphoto/";
    public static String LOG_OUT = BASE_URL + "/index.php/Api/logout";


    public static final String TOPIC_GLOBAL = "education";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
}
