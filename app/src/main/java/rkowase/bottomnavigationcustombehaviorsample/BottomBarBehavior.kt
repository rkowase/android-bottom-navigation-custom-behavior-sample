package rkowase.bottomnavigationcustombehaviorsample

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View

class BottomBarBehavior(var context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    private var defaultDependencyTop = -1

    override fun layoutDependsOn(parent: CoordinatorLayout, bottomBar: Toolbar, dependency: View): Boolean =
            dependency is AppBarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout, bottomBar: Toolbar, dependency: View): Boolean {
        if (defaultDependencyTop == -1) {
            defaultDependencyTop = dependency.top
        }
        var diff = defaultDependencyTop - dependency.top
        val minHeight = context.resources.getDimensionPixelSize(R.dimen.bottom_bar_min_height)
        if (diff > minHeight) {
            diff = minHeight
        }
        ViewCompat.setTranslationY(bottomBar, diff.toFloat())
        return true
    }

}