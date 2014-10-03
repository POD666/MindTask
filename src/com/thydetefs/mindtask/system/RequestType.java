package com.thydetefs.mindtask.system;

public enum RequestType
{
    register,
    authorize,
    info,

    give_key,
    pay_key,
    key_count,

    add_question,
    check_answer,
    get_answer,

    get_question_new,
    get_question_top,
    get_question_tresh,

    vote_difficulty,
    vote_like,
    vote_dislike,

    mark_as_favorite,
    unmark_as_favorite,
    get_favorite
}