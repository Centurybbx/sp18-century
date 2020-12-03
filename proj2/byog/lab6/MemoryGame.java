package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int randomIndex = (int) (Math.random() * this.CHARACTERS.length);
            sb.append(CHARACTERS[randomIndex]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.white);
        int headHeight = this.height - 2;
        if (!this.gameOver) {
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 20));
            StdDraw.text(3, headHeight, "Round: " + this.round);
            StdDraw.text(this.width / 2, headHeight, playerTurn ? "Type!" : "Watch!");
            StdDraw.text(this.width - 5, headHeight, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 4, width, height - 4);
        }
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        StdDraw.text(this.width / 2, this.height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(1000);
            drawFrame(" ");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        StringBuilder sb = new StringBuilder(n);
        while (StdDraw.hasNextKeyTyped()) {
            this.playerTurn = true;
            char key = StdDraw.nextKeyTyped();
            sb.append(key);
        }
        return sb.toString();
    }

    public void startGame() {
        round = 1;
        gameOver = false;
        while (!gameOver) {
            this.playerTurn = false;
            drawFrame("Round:" + round);
            StdDraw.pause(1000);

            String randomString = generateRandomString(round);
            flashSequence(randomString);

            String userInput = solicitNCharsInput(round);
            drawFrame(userInput);
            StdDraw.pause(500);
            if (userInput.equals(randomString)) {
                round += 1;
                drawFrame("Correct!");
                StdDraw.pause(1500);
            } else {
                this.gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            }
        }
    }

}
