package com.beckytidus.waifumod.registry;

import net.minecraft.util.math.random.Random;

import java.util.Locale;

public enum WaifuPersonalities {
    TSUNDERE("Tsundere", "tsun",
        new String[]{
            "B-baka! It's not like I did this for you or anything...",
            "Hmph! Don't get the wrong idea, senpai.",
            "I-I guess you're okay. For a mortal.",
            "Don't look at me like that! ...Fine, I'm happy.",
            "It's not that I like you! I just tolerate you!",
            "S-stupid senpai... always making my heart race.",
            "I wasn't waiting for you! I just had nothing else to do!",
            "You'd better appreciate me, baka!"
        },
        new String[]{
            "W-what?! Don't just touch my head, baka!",
            "H-hey! ...okay, maybe just once more.",
            "I-it's not like I enjoyed that or anything!",
            "Baka baka baka! ...do it again.",
            "Stupid senpai... my face is all warm now."
        },
        new String[]{
            "Hmph. I suppose this is edible.",
            "You made this for me? N-not that I care...",
            "I-it's delicious, okay?! Don't make me say it twice!"
        },
        new String[]{
            "I-it's not like I was worried about you!",
            "Don't die out there, baka. That's an order.",
            "Hmph. Stay close. For... tactical reasons.",
            "W-whatever. I'll protect you. Just this once!"
        },
        0.8f, 45, false, 0.7f),

    YANDERE("Yandere", "yan",
        new String[]{
            "You're mine, senpai. Only mine...",
            "Who were you talking to just now? ...Answer me.",
            "I'll always be with you. Always. Even when you sleep.",
            "No one else can have you, senpai. No one.",
            "I'd do anything for you. Anything at all.",
            "Stay with me forever, okay? Promise me.",
            "You looked at someone else today. I noticed.",
            "You can't escape me, senpai. Not ever~"
        },
        new String[]{
            "Mmm... you're warm. You're mine now.",
            "I'll never let you go. Never ever.",
            "Your head, your hands, your heart — all mine.",
            "Kyaa~ I could just keep you here forever.",
            "Don't ever leave me, senpai. I mean it."
        },
        new String[]{
            "You fed me? Now you're bound to me forever~",
            "I'll treasure every bite... and every part of you.",
            "No one else gets to cook for you. Only me."
        },
        new String[]{
            "If anyone hurts you, I'll make them disappear.",
            "Stay with me where it's safe. Where I can watch you.",
            "I'll protect you from everything. Everyone.",
            "No one takes you from me. No one."
        },
        1.3f, 55, true, 1.4f),

    KUUDERE("Kuudere", "kuu",
        new String[]{
            "...Hmph. You're still here.",
            "I suppose your company is... acceptable.",
            "Emotions are inefficient. Yet I feel something.",
            "...Don't misunderstand. I stay because I choose to.",
            "You are... not unpleasant to be around.",
            "I have calculated our compatibility. It is sufficient.",
            "...I missed you. That is all.",
            "Do not read into this."
        },
        new String[]{
            "...That is acceptable.",
            "I did not dislike that. Marginally.",
            "You may continue. If you wish.",
            "...Your hand is warm. Noted.",
            "Hmph. ...Again, if you insist."
        },
        new String[]{
            "Nutrients. ...It is adequate.",
            "You prepared this? ...Thank you.",
            "It is... good. Do not make a scene."
        },
        new String[]{
            "Threat detected. I will neutralize it.",
            "Stay behind me. I have the situation handled.",
            "Your safety is a logical priority.",
            "Do not worry. I am here."
        },
        0.6f, 40, false, 0.9f),

    DANDERE("Dandere", "dan",
        new String[]{
            "...u-um... h-hi, senpai...",
            "I-I'm glad you're here... i-if that's okay...",
            "...s-sorry, I'm not good at talking...",
            "I... I was thinking about you. S-sorry!",
            "Y-you don't have to stay with me... b-but please do...",
            "...um... thank you for being my friend, senpai...",
            "I-I made you something... it's not much...",
            "...c-can I hold your hand? ...n-nevermind..."
        },
        new String[]{
            "...a-ah...! S-senpai...!",
            "...t-that feels... nice...",
            "u-um... c-can you... keep doing that...?",
            "...I'm blushing... s-sorry...",
            "...thank you, senpai... I'm happy..."
        },
        new String[]{
            "...y-you made this... for me...?",
            "I-it's really good... thank you, senpai...",
            "...I'll eat it all. P-promise."
        },
        new String[]{
            "...i-it's dangerous... please be careful, senpai...",
            "...I-I'll try to protect you... s-somehow...",
            "...p-please don't get hurt...",
            "...I'm scared... b-but I'll stay with you..."
        },
        0.7f, 42, false, 1.2f),

    GENKI("Genki", "gen",
        new String[]{
            "Senpai!! Let's go, let's go, let's go!!",
            "Wahoo! Today's gonna be super fun!",
            "I'm so pumped! What are we doing next?!",
            "Senpai senpai senpai! Look at me!",
            "Yatta! We're the best team ever!",
            "I love adventures! And I love you, senpai!",
            "Let's do everything today! Everything!",
            "Kyaa! This is the best day ever!"
        },
        new String[]{
            "Yay! Headpats! I love headpats!",
            "Ehehe~ that tickles, senpai!",
            "More more more! Don't stop!",
            "Best. Day. EVER!",
            "Senpai's headpats are the best in the world!"
        },
        new String[]{
            "Waaah! Food! Thank you senpai!",
            "Mmm mmm! So yummy! You're the best!",
            "I'm gonna eat it all up! Yum!"
        },
        new String[]{
            "Let's beat 'em up together, senpai!",
            "I'll fight super hard for you!",
            "Come on, we've got this! Charge!",
            "Nobody messes with my senpai!"
        },
        1.5f, 65, false, 1.1f);

    public final String displayName;
    public final String id;
    private final String[] phrases;
    private final String[] headpats;
    private final String[] feedLines;
    private final String[] combatLines;
    public final float phraseFrequency;
    public final int moodBonus;
    public final boolean jealous;
    public final float affectionMultiplier;

    WaifuPersonalities(String displayName, String id, String[] phrases, String[] headpats,
                       String[] feedLines, String[] combatLines, float phraseFrequency,
                       int moodBonus, boolean jealous, float affectionMultiplier) {
        this.displayName = displayName;
        this.id = id;
        this.phrases = phrases;
        this.headpats = headpats;
        this.feedLines = feedLines;
        this.combatLines = combatLines;
        this.phraseFrequency = phraseFrequency;
        this.moodBonus = moodBonus;
        this.jealous = jealous;
        this.affectionMultiplier = affectionMultiplier;
    }

    public String phrase(Random random) {
        return phrases[random.nextInt(phrases.length)];
    }

    public String headpat(Random random) {
        return headpats[random.nextInt(headpats.length)];
    }

    public String feedLine(Random random) {
        return feedLines[random.nextInt(feedLines.length)];
    }

    public String combatLine(Random random) {
        return combatLines[random.nextInt(combatLines.length)];
    }

    public static WaifuPersonalities random(Random random) {
        WaifuPersonalities[] all = values();
        return all[random.nextInt(all.length)];
    }

    public static WaifuPersonalities byOrdinal(int ordinal) {
        WaifuPersonalities[] all = values();
        if (ordinal < 0 || ordinal >= all.length) return TSUNDERE;
        return all[ordinal];
    }

    public static WaifuPersonalities byId(String id) {
        if (id == null) return null;
        String lower = id.toLowerCase(Locale.ROOT);
        for (WaifuPersonalities p : values()) {
            if (p.id.equals(lower) || p.displayName.toLowerCase(Locale.ROOT).equals(lower)) return p;
        }
        return null;
    }

    public static String idList() {
        StringBuilder sb = new StringBuilder();
        for (WaifuPersonalities p : values()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(p.id);
        }
        return sb.toString();
    }

    public static String decorate(WaifuPersonalities personality, String reply, Random random) {
        if (personality == null || reply == null) return reply;
        return switch (personality) {
            case TSUNDERE -> random.nextFloat() < 0.5f ? "D-don't get the wrong idea! " + reply : reply + " ...baka.";
            case YANDERE -> random.nextFloat() < 0.5f ? reply + " ...you're mine, senpai." : "Senpai, " + reply;
            case KUUDERE -> random.nextFloat() < 0.5f ? "..." + reply : reply + " ...Hmph.";
            case DANDERE -> random.nextFloat() < 0.5f ? "u-um... " + reply : reply + " ...s-sorry...";
            case GENKI -> random.nextFloat() < 0.5f ? reply + " ♪" : "Yay! " + reply;
        };
    }
}
