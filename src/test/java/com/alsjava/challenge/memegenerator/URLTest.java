package com.alsjava.challenge.memegenerator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

/**
 * Created by aluis on 12/29/19.
 */
class URLTest {

    public static final List<Meme> MEMES = List.of(new Meme("Drake Hotline Bling", "https://i.imgflip.com/30b1gx.jpg"),
            new Meme("Two Buttons", "https://i.imgflip.com/1g8my4.jpg"),
            new Meme("Mocking Spongebob", "https://i.imgflip.com/1otk96.jpg"),
            new Meme("Change My Mind", "https://i.imgflip.com/24y43o.jpg"),
            new Meme("Left Exit 12 Off Ramp", "https://i.imgflip.com/22bdq6.jpg"),
            new Meme("Expanding Brain", "https://i.imgflip.com/1jwhww.jpg"),
            new Meme("Batman Slapping Robin", "https://i.imgflip.com/9ehk.jpg"),
            new Meme("Blank Nut Button", "https://i.imgflip.com/1yxkcp.jpg"),
            new Meme("Running Away Balloon", "https://i.imgflip.com/261o3j.jpg"),
            new Meme("Surprised Pikachu", "https://i.imgflip.com/2kbn1e.jpg"),
            new Meme("Boardroom Meeting Suggestion", "https://i.imgflip.com/m78d.jpg"),
            new Meme("Spongebob Ight Imma Head Out", "https://i.imgflip.com/392xtu.jpg"),
            new Meme("Waiting Skeleton", "https://i.imgflip.com/2fm6x.jpg"),
            new Meme("Roll Safe Think About It", "https://i.imgflip.com/1h7in3.jpg"),
            new Meme("American Chopper Argument", "https://i.imgflip.com/2896ro.jpg"),
            new Meme("X, X Everywhere", "https://i.imgflip.com/1ihzfe.jpg"),
            new Meme("Inhaling Seagull", "https://i.imgflip.com/1w7ygt.jpg"),
            new Meme("One Does Not Simply", "https://i.imgflip.com/1bij.jpg"),
            new Meme("Is This A Pigeon", "https://i.imgflip.com/1o00in.jpg"),
            new Meme("Disaster Girl", "https://i.imgflip.com/23ls.jpg"),
            new Meme("Hide the Pain Harold", "https://i.imgflip.com/gk5el.jpg"),
            new Meme("The Scroll Of Truth", "https://i.imgflip.com/21tqf4.jpg"),
            new Meme("Ancient Aliens", "https://i.imgflip.com/26am.jpg"),
            new Meme("Marked Safe From", "https://i.imgflip.com/2odckz.jpg"),
            new Meme("Y'all Got Any More Of That", "https://i.imgflip.com/21uy0f.jpg"),
            new Meme("The Rock Driving", "https://i.imgflip.com/grr.jpg"),
            new Meme("Oprah You Get A", "https://i.imgflip.com/gtj5t.jpg"),
            new Meme("Unsettled Tom", "https://i.imgflip.com/2wifvo.jpg"),
            new Meme("Trump Bill Signing", "https://i.imgflip.com/1ii4oc.jpg"),
            new Meme("Futurama Fry", "https://i.imgflip.com/1bgw.jpg"),
            new Meme("Third World Skeptical Kid", "https://i.imgflip.com/265k.jpg"),
            new Meme("Finding Neverland", "https://i.imgflip.com/3pnmg.jpg"),
            new Meme("Who Killed Hannibal", "https://i.imgflip.com/28s2gu.jpg"),
            new Meme("The Most Interesting Man In The World", "https://i.imgflip.com/1bh8.jpg"),
            new Meme("Evil Kermit", "https://i.imgflip.com/1e7ql7.jpg"),
            new Meme("Hard To Swallow Pills", "https://i.imgflip.com/271ps6.jpg"),
            new Meme("Star Wars Yoda", "https://i.imgflip.com/8k0sa.jpg"),
            new Meme("First World Problems", "https://i.imgflip.com/1bhf.jpg"),
            new Meme("Dont You Squidward", "https://i.imgflip.com/26br.jpg"),
            new Meme("Black Girl Wat", "https://i.imgflip.com/8h0c8.jpg"),
            new Meme("Grandma Finds The Internet", "https://i.imgflip.com/1bhw.jpg"),
            new Meme("That Would Be Great", "https://i.imgflip.com/c2qn.jpg"),
            new Meme("Brace Yourselves X is Coming", "https://i.imgflip.com/1bhm.jpg"),
            new Meme("Leonardo Dicaprio Cheers", "https://i.imgflip.com/39t1o.jpg"),
            new Meme("But Thats None Of My Business", "https://i.imgflip.com/9sw43.jpg"),
            new Meme("Epic Handshake", "https://i.imgflip.com/28j0te.jpg"),
            new Meme("Who Would Win?", "https://i.imgflip.com/1ooaki.jpg"),
            new Meme("Y U No", "https://i.imgflip.com/1bh3.jpg"),
            new Meme("Me And The Boys", "https://i.imgflip.com/320xfw.jpg"),
            new Meme("Laughing Men In Suits", "https://i.imgflip.com/jrj7.jpg"),
            new Meme("Third World Success Kid", "https://i.imgflip.com/265j.jpg"),
            new Meme("Doge", "https://i.imgflip.com/4t0m5.jpg"),
            new Meme("Evil Toddler", "https://i.imgflip.com/51s5.jpg"),
            new Meme("Bad Luck Brian", "https://i.imgflip.com/1bip.jpg"),
            new Meme("Face You Make Robert Downey Jr", "https://i.imgflip.com/5mcpl.jpg"),
            new Meme("Dr Evil Laser", "https://i.imgflip.com/odluv.jpg"),
            new Meme("X All The Y", "https://i.imgflip.com/1bh9.jpg"),
            new Meme("Creepy Condescending Wonka", "https://i.imgflip.com/1bim.jpg"),
            new Meme("Say it Again, Dexter", "https://iMeme.imgflip.com/16iyn1.jpg"),
            new Meme("Jack Sparrow Being Chased", "https://i.imgflip.com/9vct.jpg"),
            new Meme("This Is Where I'd Put My Trophy If I Had One", "https://i.imgflip.com/1wz1x.jpg"),
            new Meme("Scared Cat", "https://i.imgflip.com/2hgfw.jpg"),
            new Meme("Well Yes, But Actually No", "https://i.imgflip.com/2tn11b.jpg"),
            new Meme("Simba Shadowy Place", "https://i.imgflip.com/7yk6.jpg"),
            new Meme("Maury Lie Detector", "https://i.imgflip.com/9iz9.jpg"),
            new Meme("Matrix Morpheus", "https://i.imgflip.com/25w3.jpg"),
            new Meme("Sleeping Shaq", "https://i.imgflip.com/1nck6k.jpg"),
            new Meme("Imagination Spongebob", "https://i.imgflip.com/3i7p.jpg"),
            new Meme("Success Kid", "https://i.imgflip.com/1bhk.jpg"),
            new Meme("Presidential Alert", "https://i.imgflip.com/2m20oc.jpg"),
            new Meme("Put It Somewhere Else Patrick", "https://i.imgflip.com/1bil.jpg"),
            new Meme("Squidward", "https://i.imgflip.com/64ku.jpg"),
            new Meme("Grumpy Cat", "https://i.imgflip.com/8p0a.jpg"),
            new Meme("Be Like Bill", "https://i.imgflip.com/xh3me.jpg"),
            new Meme("Uncle Sam", "https://i.imgflip.com/1x6f.jpg"),
            new Meme("Marvel Civil War 1", "https://i.imgflip.com/govs4.jpg"),
            new Meme("See Nobody Cares", "https://i.imgflip.com/3vzej.jpg"),
            new Meme("Too Damn High", "https://i.imgflip.com/1bik.jpg"),
            new Meme("Philosoraptor", "https://i.imgflip.com/1bgs.jpg"),
            new Meme("Mugatu So Hot Right Now", "https://i.imgflip.com/cv1y0.jpg"),
            new Meme("Yo Dawg Heard You", "https://i.imgflip.com/26hg.jpg"),
            new Meme("Surprised Koala", "https://i.imgflip.com/ljk.jpg"),
            new Meme("Pentagon Hexagon Octagon", "https://i.imgflip.com/2klb17.jpg"),
            new Meme("Good Fellas Hilarious", "https://i.imgflip.com/s4f1k.jpg"),
            new Meme("Look At Me", "https://i.imgflip.com/hmt3v.jpg"),
            new Meme("Mr Krabs Blur Meme", "https://i.imgflip.com/10r5wh.jpg"),
            new Meme("Sparta Leonidas", "https://i.imgflip.com/46rh.jpg"),
            new Meme("Arthur Fist", "https://i.imgflip.com/1866qe.jpg"),
            new Meme("Am I The Only One Around Here", "https://i.imgflip.com/5kdc.jpg"),
            new Meme("Captain Picard Facepalm", "https://i.imgflip.com/wczz.jpg"),
            new Meme("Picard Wtf", "https://i.imgflip.com/59qi.jpg"),
            new Meme("Kevin Hart", "https://i.imgflip.com/4bh6h.jpg"),
            new Meme("Bird Box", "https://i.imgflip.com/2puag9.jpg"),
            new Meme("Ill Just Wait Here", "https://i.imgflip.com/2cp1.jpg"),
            new Meme("And Just Like That", "https://i.imgflip.com/we0ps.jpg"),
            new Meme("What Do We Want", "https://i.imgflip.com/bfq76.jpg"),
            new Meme("Star Wars No", "https://i.imgflip.com/bfexh.jpg"));

    @Test
    void downloadMemeFromNetwork() {
        MEMES.forEach(meme -> {
            try {
                File memeFile = new File("./memes/" + meme.getName() + ".jpg");
                URL url = new URL(meme.getUrl());
                ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(memeFile);
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            } catch (Exception ignored) {
            }
        });
    }

    private static class Meme {
        private String name;
        private String url;

        public Meme(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
