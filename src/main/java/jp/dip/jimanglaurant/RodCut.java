package jp.dip.jimanglaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mare
 */
public class RodCut {

    private final int[] values;
    private final int length;

    public RodCut(int[] values, int length) {
        this.values = values;
        this.length = length;
    }

    //虱潰し
    public int thoroughly() {
        //初期値の準備
        int size = (int) Math.pow(2, length - 1);
        boolean[] s = new boolean[length - 1];
        Arrays.fill(s, false);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            //listに切り出したロッドの長さが入るように下準備
            List<Integer> list = new ArrayList<>();
            int a = 0;
            for (int j = 0; j < s.length; j++) {
                a++;
                if (s[j]) {
                    list.add(a);
                    a = 0;
                }
                if (j == s.length - 1) {
                    list.add(a + 1);
                }
            }
            if (list.size() == 0) {
                list.add(length);
            }
            //列挙を一つ進める
            add(s);
            //価値表より長いものを含む切り出しは必要ないので飛ばす
            if (!list.stream().allMatch(b -> b < values.length)) {
                continue;
            }

            //合計を比較しmaxを取る
            max = Math.max(max, list.stream().mapToInt(m -> values[m]).sum());
        }
        return max;
    }

    private void add(boolean[] array) {
        boolean f = false;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || f) {
                f = array[i];
                array[i] = !array[i];
                continue;
            }
        }
    }

    //再帰
    public int recursively() {
        return rec(length);
    }

    private int rec(int n) {
        if (n <= 0) return 0;
        int ans = Integer.MIN_VALUE;
        //一番前の切り出しの長さは価値表より短い場合のみ調べる
        for (int i = 1; i < Math.min(values.length, n + 1); i++) {
            ans = Math.max(ans, values[i] + rec(n - i));
        }
        return ans;
    }

    //ボトムアップ
    public int bottomUp() {
        int[] ans = new int[length + 1];
        ans[0] = 0;
        for (int i = 1; i < length + 1; i++) {
            int a = Integer.MIN_VALUE;
            //一番前の切り出しの長さは価値表より短い場合のみ調べる
            for (int j = 1; j < Math.min(i + 1, values.length); j++) {
                a = Math.max(a, values[j] + ans[i - j]);
            }
            ans[i] = a;
        }
        return ans[length];
    }

    //トップダウン
    public int topDonw() {
        int[] r = new int[length + 1];
        Arrays.fill(r, Integer.MIN_VALUE);
        r[0] = 0;
        return rec(length, r);
    }

    private int rec(int n, int[] r) {
        if (n <= 0) return 0;
        int ans = Integer.MIN_VALUE;
        //一番前の切り出しの長さは価値表より短い場合のみ調べる
        for (int i = 1; i < Math.min(values.length, n + 1); i++) {
            if (r[n - i] == Integer.MIN_VALUE) {
                ans = Math.max(ans, values[i] + rec(n - i, r));
            } else {
                ans = Math.max(ans, values[i] + r[n - i]);
            }
            //表更新
            r[n] = Math.max(ans, r[n]);
        }
        return ans;
    }
}
