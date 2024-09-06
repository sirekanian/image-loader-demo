package org.sirekanyan.imageloader.internal.extensions

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

internal interface MyLifecycleCallbacks : ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // noop
    }

    override fun onActivityStarted(activity: Activity) {
        // noop
    }

    override fun onActivityResumed(activity: Activity) {
        // noop
    }

    override fun onActivityPaused(activity: Activity) {
        // noop
    }

    override fun onActivityStopped(activity: Activity) {
        // noop
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // noop
    }

    override fun onActivityDestroyed(activity: Activity) {
        // noop
    }
}
