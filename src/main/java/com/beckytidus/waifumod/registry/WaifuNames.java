package com.beckytidus.waifumod.registry;

import net.minecraft.util.math.random.Random;

import java.util.List;

public final class WaifuNames {
    private static final List<String> NAMES = List.of(
        "Sakura", "Yuki", "Rin", "Hinata", "Mikasa", "Asuna", "Rem", "Ram", "Emilia", "Zero Two",
        "Nezuko", "Kanao", "Shinobu", "Mitsuri", "Rukia", "Orihime", "Nami", "Robin", "Hancock", "Vivi",
        "Tsunade", "Sakurako", "Ayaka", "Ganyu", "Raiden", "Hu Tao", "Fischl", "Amber", "Noelle", "Barbara",
        "Keqing", "Mona", "Yae", "Kokomi", "Yoimiya", "Ayaka", "Shenhe", "Yelan", "Nilou", "Nahida",
        "Furina", "Clorinde", "Frieren", "Fern", "Yor", "Anya", "Maki", "Nobara", "Tohru", "Kobayashi",
        "Aqua", "Megumin", "Darkness", "Wiz", "Yunyun", "Chitoge", "Onodera", "Marika", "Haruhi", "Mikuru",
        "Yuki", "Nagato", "Mio", "Ritsu", "Yui", "Azusa", "Tsumugi", "Nodoka", "Ui", "Sawako",
        "Shizuku", "Kaori", "Miyako", "Aoi", "Ichigo", "Kokoro", "Nino", "Miku", "Itsuki", "Yotsuba",
        "Kaguya", "Chika", "Ai", "Mai", "Tohka", "Kurumi", "Yoshino", "Origami", "Kotori", "Rias",
        "Akeno", "Koneko", "Asia", "Xenovia", "Irina", "Rukia", "Hisana", "Yoruichi", "Soi", "Nel",
        "Rukia", "Senjougahara", "Hanekawa", "Kanbaru", "Sengoku", "Karen", "Tsukihi", "Shinobu", "Mayoi", "Nadeko",
        "Asuka", "Rei", "Mari", "Misato", "Ritsuko", "Hikari", "Kaworu", "Pen", "Chihiro", "Yubaba",
        "Satsuki", "Mei", "Kiki", "Sophie", "Fio", "Nausicaa", "Sheeta", "Pazu", "San", "Eboshi",
        "Uraraka", "Momo", "Kyoka", "Tsuyu", "Mina", "Toru", "Nejire", "Himiko", "Lady", "Inko",
        "Bulma", "Chi-Chi", "Android", "Videl", "Pan", "Caulifla", "Kale", "Ribrianne", "Launch", "Mai",
        "Nami", "Perona", "Rebecca", "Shirahoshi", "Pudding", "Carrot", "Yamato", "Uta", "Koala", "Bonney",
        "Erza", "Lucy", "Wendy", "Mirajane", "Juvia", "Levy", "Cana", "Ultear", "Kagura", "Minerva",
        "Nezuko", "Tanjiro", "Zenitsu", "Inosuke", "Genya", "Murata", "Aoi", "Tamayo", "Yushiro", "Susamaru",
        "Power", "Makima", "Reze", "Kobeni", "Himeno", "Quanxi", "Aki", "Angel", "Beam", "Princi",
        "Tohru", "Elma", "Ilulu", "Lucoa", "Kanna", "Shouta", "Fafnir", "Makoto", "Azad", "Damocles"
    );

    public static String pickRandom(Random random) {
        return NAMES.get(random.nextInt(NAMES.size()));
    }
}
