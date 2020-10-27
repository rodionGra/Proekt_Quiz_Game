package space.quiz;


public class Array {

    //массив первого уровня
    final int[] images1 = {
        R.drawable.first_lvl_zero,
        R.drawable.first_lvl_one,
        R.drawable.first_lvl_two,
        R.drawable.first_lvl_three,
        R.drawable.first_lvl_four,
        R.drawable.first_lvl_five,
        R.drawable.first_lvl_six,
        R.drawable.first_lvl_seven,
        R.drawable.first_lvl_eight,
        R.drawable.first_lvl_nine,
    };

    final int[] texts1 = {
            R.string.lvl1text0,
            R.string.lvl1text1,
            R.string.lvl1text2,
            R.string.lvl1text3,
            R.string.lvl1text4,
            R.string.lvl1text5,
            R.string.lvl1text6,
            R.string.lvl1text7,
            R.string.lvl1text8,
            R.string.lvl1text9,
    };

    //массив второго уровня
    final int[] images2 = {
            R.drawable.second_lvl_one,
            R.drawable.second_lvl_two,
            R.drawable.second_lvl_three,
            R.drawable.second_lvl_four,
            R.drawable.second_lvl_five,
            R.drawable.second_lvl_six,
            R.drawable.second_lvl_seven,
            R.drawable.second_lvl_eight,
            R.drawable.second_lvl_nine,
            R.drawable.second_lvl_ten
    };

    final int[] texts2 = {
            R.string.lvl2text1,
            R.string.lvl2text2,
            R.string.lvl2text3,
            R.string.lvl2text4,
            R.string.lvl2text5,
            R.string.lvl2text6,
            R.string.lvl2text7,
            R.string.lvl2text8,
            R.string.lvl2text9,
            R.string.lvl2text10
    };

    //массив 3 уровня
    final int[] images3 = {
            R.drawable.third_lvl_1, R.drawable.third_lvl_2, R.drawable.third_lvl_3,
            R.drawable.third_lvl_4, R.drawable.third_lvl_5, R.drawable.third_lvl_6,
            R.drawable.third_lvl_7, R.drawable.third_lvl_8, R.drawable.third_lvl_9,
            R.drawable.third_lvl_10, R.drawable.third_lvl_11, R.drawable.third_lvl_12,
            R.drawable.third_lvl_13, R.drawable.third_lvl_14, R.drawable.third_lvl_15,
            R.drawable.third_lvl_16, R.drawable.third_lvl_17, R.drawable.third_lvl_18,
            R.drawable.third_lvl_19, R.drawable.third_lvl_20, R.drawable.third_lvl_21
    };

    final int[] texts3 = {
            R.string.lvl3text1, R.string.lvl3text2, R.string.lvl3text3,
            R.string.lvl3text4, R.string.lvl3text5, R.string.lvl3text6,
            R.string.lvl3text7, R.string.lvl3text8, R.string.lvl3text9,
            R.string.lvl3text10, R.string.lvl3text11, R.string.lvl3text12,
            R.string.lvl3text13, R.string.lvl3text14, R.string.lvl3text15,
            R.string.lvl3text16, R.string.lvl3text17, R.string.lvl3text18,
            R.string.lvl3text19, R.string.lvl3text20, R.string.lvl3text21
    };

    //массив 3 уровня
    final int[] images4 = {
            R.drawable.four_lvl_1, R.drawable.four_lvl_2, R.drawable.four_lvl_3,
            R.drawable.four_lvl_4, R.drawable.four_lvl_5, R.drawable.four_lvl_6,
            R.drawable.four_lvl_7, R.drawable.four_lvl_8, R.drawable.four_lvl_9,
            R.drawable.four_lvl_10, R.drawable.four_lvl_11, R.drawable.four_lvl_12,
            R.drawable.four_lvl_13, R.drawable.four_lvl_14, R.drawable.four_lvl_15,
            R.drawable.four_lvl_16
    };

    final int[] texts4 = {
            R.string.lvl4text1, R.string.lvl4text2, R.string.lvl4text3,
            R.string.lvl4text4, R.string.lvl4text5, R.string.lvl4text6,
            R.string.lvl4text7, R.string.lvl4text8, R.string.lvl4text9,
            R.string.lvl4text10, R.string.lvl4text11, R.string.lvl4text12,
            R.string.lvl4text13, R.string.lvl4text14, R.string.lvl4text15,
            R.string.lvl4text16
    };

    /*public Drawable getDrawableFromAssets(String path) throws IOException {
        Drawable img = Drawable.createFromStream(getAssets().open(path), null);
        return img;
    }*/
}
