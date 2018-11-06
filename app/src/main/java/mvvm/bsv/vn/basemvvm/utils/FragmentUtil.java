package mvvm.bsv.vn.basemvvm.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtil {
    private final static FragmentUtil instance = new FragmentUtil();

    /**
     * Get instance fragment util.
     *
     * @return the fragment util
     */
    public static FragmentUtil getInstance() {
        return instance;
    }

    /**
     * Replace fragment.
     *
     * @param fragmentManager the transaction
     * @param fragment        the fragment
     * @param viewContainer   the view container
     */
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .replace(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commitAllowingStateLoss();
    }

    public void replaceFragmentImmediate(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .replace(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commitAllowingStateLoss();
    }


    public void addFragment(FragmentManager fragmentManager, Fragment fragment, int viewContainer, int enterAnim, int exitAnim) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .setCustomAnimations(enterAnim, exitAnim, enterAnim, exitAnim)
                .hide(getCurrentFragment(fragmentManager))
                .add(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                .commit();
    }

    public void addFragmentImmediate(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .hide(getCurrentFragment(fragmentManager))
                .add(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                .commit();
    }

    public void addFragmentImmediateNoHide(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                .commit();
    }




    /**
     * Replace child fragment.
     *
     * @param parentFragment the parent fragment
     * @param childFragment  the child fragment
     * @param viewContainer  the view container
     */
    public void replaceChildFragment(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment
                .getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(FRAGMENT_TAG)
                .replace(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    public void replaceChildFragmentBonus(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment
                .getChildFragmentManager().beginTransaction()
                .addToBackStack(FRAGMENT_TAG)
                .replace(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    public void addChildFragment(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment
                .getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(FRAGMENT_TAG)
                .add(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * Replace child fragment without add to back stack.
     *
     * @param parentFragment the parent fragment
     * @param childFragment  the child fragment
     * @param viewContainer  the view container
     */
    public void replaceChildFragmentWithoutAddToBackStack(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment
                .getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    public void onBackPressJumpToFragmentWithName(FragmentManager fragmentManager, String fragmentName) {
        while (fragmentManager.getBackStackEntryCount() > 1) {
            if (fragmentManager.getBackStackEntryAt(
                    fragmentManager.getBackStackEntryCount() - 1).getName().equals(fragmentName)) {
                return;
            }
            try {
                fragmentManager.popBackStackImmediate();
            } catch (IllegalStateException ex) {
                ex.getMessage();
            }
        }
    }

    public Fragment getFragmentBackStack(FragmentManager fragmentManager, int index) {
        String FRAGMENT_TAG = fragmentManager.getBackStackEntryAt(index).getName();
        return fragmentManager.findFragmentByTag(FRAGMENT_TAG);
    }

    public void replaceFragmentAfterResetBackstack(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        resetBackstack(fragmentManager);
        replaceFragmentImmediate(fragmentManager, fragment, viewContainer);
    }

    public void resetBackstack(FragmentManager fragmentManager) {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void backStackToMain(FragmentManager fragmentManager) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount() - 1; ++i) {
            fragmentManager.popBackStack();
        }
        fragmentManager.beginTransaction().commitAllowingStateLoss();
    }

    public void backToFragmentTagInStack(Fragment fragment, FragmentManager fragmentManager) {
        String TAG = fragment.getClass().getSimpleName();
        for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 1; i--) {
            String FRAGMENT_TAG = fragmentManager.getBackStackEntryAt(i).getName();
            if (FRAGMENT_TAG.equals(TAG)) {
                break;
            } else {
                fragmentManager.popBackStack();
            }
        }
        fragmentManager.beginTransaction().commitAllowingStateLoss();
    }

    public void backToFragmentTagInStack2(Fragment fragment, FragmentManager fragmentManager) {
        String TAG = fragment.getClass().getSimpleName();
        for (int i = fragmentManager.getBackStackEntryCount() - 1; i > 0; i--) {
            String FRAGMENT_TAG = fragmentManager.getBackStackEntryAt(i).getName();
            if (FRAGMENT_TAG.equals(TAG)) {
                break;
            } else {
                fragmentManager.popBackStack();
            }
        }
        fragmentManager.beginTransaction().commitAllowingStateLoss();
    }

    public Fragment getCurrentFragment(FragmentManager fragmentManager) {
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            String FRAGMENT_TAG = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            return fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        }
        return null;
    }
}
