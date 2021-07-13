package com.haryop.synpulsefrontendchallenge.utils

object ConstantsObj {
    const val TO_SIGN_IN = 1
    const val TO_SIGN_UP = 2

    const val KEY_PREVIOUS_ACTIVITY = "prev_activity"
    const val VALUE_FROM_SIGNINUP_ACTIVITY = 1
    const val VALUE_FROM_SPLASHSCREEN_ACTIVITY = 2

    const val KEY_TARGET_ACTIVITY = "target_activity"
    const val VALUE_SIGNINUP_ACTIVITY = 1
    const val VALUE_SPLASHSCREEN_ACTIVITY = 2
    const val VALUE_TITLESCREEN_ACTIVITY = 3
    const val VALUE_BROWSECOMP_ACTIVITY = 4
    const val VALUE_WELCOMESEARCH_ACTIVITY = 5
    const val VALUE_PROFILE_ACTIVITY = 6

    const val ALPHAVANTAGE_APIKEY = "${APIKEY_Obj.YOUR_ALPHAVANTAGE_APIKEY}"
    const val ALPHAVANTAGE_BASEPARAM = "query?apikey=${ConstantsObj.ALPHAVANTAGE_APIKEY}"

}