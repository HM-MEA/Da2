package jp.dip.jimanglaurant;

import org.junit.Test;

/**
 * Created by mare
 */
public class RodCutTest {

    @Test
    public void test1() {
        RodCut rodCut = new RodCut(new int[]{0, 1, 5, 8, 9, 10}, 10);
        int ans = 0;
        for (int i = 0; i < 10000; i++) {
            ans = rodCut.thoroughly();
        }
        System.out.println(ans);
    }

    @Test
    public void test2() {
        RodCut rodCut = new RodCut(new int[]{0, 1, 5, 8, 9, 10}, 10);
        int ans = 0;
        for (int i = 0; i < 10000; i++) {
            ans = rodCut.recursively();
        }
        System.out.println(ans);
    }

    @Test
    public void test3() {
        RodCut rodCut = new RodCut(new int[]{0, 1, 5, 8, 9, 10}, 10);
        int ans = 0;
        for (int i = 0; i < 10000; i++) {
            ans = rodCut.bottomUp();
        }
        System.out.println(ans);
    }

    @Test
    public void test4() {
        RodCut rodCut = new RodCut(new int[]{0, 1, 5, 8, 9, 10}, 10);
        int ans = 0;
        for (int i = 0; i < 10000; i++) {
            ans = rodCut.topDonw();
        }
        System.out.println(ans);
    }
}
