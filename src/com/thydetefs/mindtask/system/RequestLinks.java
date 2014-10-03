package com.thydetefs.mindtask.system;

import android.util.Log;

import java.net.URI;
import java.util.Dictionary;
import java.util.Enumeration;

public class RequestLinks
{
    private static String server = "http://176.37.43.7";

    private static String register  = "/users/add/USERNAME/PASSWORD/";                          //- реєстрація
    private static String authorize = "/users/getid/USERNAME/PASSWORD/";                        //- авторизація(вертає id)
    private static String info      = "/users/info/USER_ID/";                                   //- інфа про користувача

    private static String give_key  = "/users/key/add/USER_ID/KEY_COUNT/";                      //- додати юзеру ключі
    private static String pay_key   = "/users/key/delete/USER_ID/KEY_COUNT/";                   //- видалити юзеру ключі
    private static String key_count = "/users/key/get/USER_ID/";                                //- кількість ключів юзера

    private static String add_question  = "/questions/add/USER_ID/QUESTION/ANSWER/";            // - додати запитання(запитання і відповідь одним словом(вживати тільки букви і цифри)
    private static String check_answer  = "/questions/answer/check/USER_ID/QUESTION_ID/";       // - перевірка правильності відповіді
    private static String get_answer    = "/questions/answer/get/USER_ID/QUESTION_ID/";         // - дістати відповідь

    private static String get_question_new   = "/questions/new/get/USER_ID/";                   // - дістати 3 питання з нових
    private static String get_question_top   = "/questions/normal/get/USER_ID/";                // - дістати 3 питання з топу
    private static String get_question_tresh = "/questions/trash/get/USER_ID/";                 // - дістати 3 з трешу

    private static String vote_difficulty = "/questions/vote/USER_ID/QUESTION_ID/RATING/";      // - проголосувати за запитання(RATING - число від 1 до 5)
    private static String vote_like       = "/questions/like/USER_ID/QUESTION_ID/";             // - лайкнути
    private static String vote_dislike    = "/questions/dislike/USER_ID/QUESTION_ID/";          // - дізлайкнути

    private static String mark_as_favorite   = "/question/favorite/add/USER_ID/QUESTION_ID/";   // - додати в вибране
    private static String unmark_as_favorite = "/question/favorite/delete/USER_ID/QUESTION_ID/";// - видалити з вибраного
    private static String get_favorite       = "/question/favorite/get/USER_ID/";               // - отримати вибрані

    //------------------------------------------------------------------------------------------------------------------

    private static String select_link(RequestType type)
    {
        switch(type)
        {
            case register:
                return register;
            case authorize:
                return authorize;
            case info:
                return info;

            case give_key:
                return give_key;
            case pay_key:
                return pay_key;
            case key_count:
                return key_count;

            case add_question:
                return add_question;
            case check_answer:
                return check_answer;
            case get_answer:
                return get_answer;

            case get_question_new:
                return get_question_new;
            case get_question_top:
                return get_question_top;
            case get_question_tresh:
                return get_question_tresh;

            case vote_difficulty:
                return vote_difficulty;
            case vote_like:
                return vote_like;
            case vote_dislike:
                return vote_dislike;

            case mark_as_favorite:
                return mark_as_favorite;
            case unmark_as_favorite:
                return unmark_as_favorite;
            case get_favorite:
                return get_favorite;
        }

        return "error";
    }

    public static URI Create_uri(RequestType request_type, Dictionary<RequestParamType, String> params)
    {
        String request_link = select_link(request_type);

        RequestParamType key;
        String value;
        Enumeration<RequestParamType> keys =  params.keys();
        while(keys.hasMoreElements())
        {
            key = keys.nextElement();
            value = params.get(key);
            Log.e("???", key.toString());
            Log.e("!!!", value);

            request_link = request_link.replace(key.toString(), value);
        }

        return URI.create(server + request_link);
    }
}
