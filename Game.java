import java.util.Scanner;

public class Game {

    private Map tower = new Map(11, 11);
    private Room currentroom;
    private int roomsvisited;
    private Inventory utory = new Inventory();
    private Position user;
    private Score uscore;
    Position room;
    boolean solved = false;
    static Room[] rooms = new Room[11];

    public Game() {
        user = new Position(10, 0);
        room = new Position(0, 0);
        currentroom = new Room("Dungeon", "jd", 'D', user);
        this.uscore = new Score(100);
        tower.world();
        roomsvisited = 0;
    }

    public static void main(String args[]) {
        Game play = new Game();
        play.startGame();
        Scanner text = new Scanner(System.in);
        String uinput = text.nextLine();
        boolean endgame = false;
        while (!endgame) {
            switch (uinput) {
                case ("help") ->
                    System.out.println(play.help());
                case ("map") ->
                    System.out.println(play.getMap().display());
                case ("look") ->
                    System.out.println(play.look());
                // first room 
                case ("look cage") ->
                    System.out.println(rooms[0].getFeature());
                case ("look key") -> {
                    System.out.println(rooms[0].getItem());
                    play.getInventory().addItem("Key");
                    System.out.println("You have picked up the key");
                }
                case ("open cage") ->
                    System.out.println("The snake has escaped");
                // second room 
                case ("look barrels") -> {
                    System.out.println(rooms[1].getFeature());
                    System.out.println("You need to show that you've learnt your lesson, overdrinking is not good for you");
                    while (!play.getSolved()) {
                        uinput = text.nextLine();
                        if (uinput.contains("i will never drink again")) {
                            play.setSolved(true);
                        }
                        System.out.println((String) play.complete_puzzle(play.getSolved()));
                    }
                }
                case ("look wine") -> {
                    rooms[1].getItem();
                    play.getInventory().addItem("Wine");
                    System.out.println("You have picked up the wine");
                }
                // third room 
                case ("wave hands") ->
                    System.out.println(play.complete_puzzle(true));
                // forth room 
                case ("look table") ->
                    System.out.println(rooms[3].getFeature());
                case ("give wine") -> {
                    play.getInventory().removeItem("Wine");
                    System.out.println("You have given the cook the wine");
                    System.out.println("There are herbs still on the table");
                }
                case ("look herbs") -> {
                    rooms[3].getItem();
                    play.getInventory().addItem("Herbs");
                    System.out.println("You have picked up the herbs");
                }
// fifth room
                case ("look bed") -> {
                    rooms[4].getFeature();
                    System.out.println("Get some shut eye.");
                }
                // sixth room 
                case ("look letter") -> {
                    play.setSolved(false);
                    while (!play.getSolved()) {
                        uinput = text.nextLine();
                        if (uinput.contains("i love you")) {
                            play.setSolved(true);
                        }
                        System.out.println(play.complete_puzzle(play.getSolved()));
                    }
                }
                // room seven
                case ("look vines") -> {
                    System.out.println(rooms[6].getFeature());
                    System.out.println("One of the vines was in fact the snake from the dungon and it bit you, quick and use your herbs before you die");
                }
                case ("use herbs") -> {
                    play.getInventory().removeItem("Herbs");
                    System.out.println("You have used the herbs and overcome the worst of the effects of the bite");
                }
                // room 8 
                case ("look figure") ->
                    System.out.println(rooms[7].getFeature());
                case ("look scythe") -> {
                    System.out.println(rooms[7].getItem());
                    play.getInventory().addItem("Scythe");
                    System.out.println("You have fought the figure and been victorious!");
                }
                // room 9 
                case ("look bench") -> {
                    System.out.println(rooms[8].getFeature());
                    play.setSolved(false);
                    while (!play.getSolved()) {
                        uinput = text.nextLine();
                        if (uinput.contains("middle")) {
                            play.setSolved(true);
                        }
                        System.out.println((String) play.complete_puzzle(play.getSolved()));
                    }
                }
                // room 10
                case ("remove flies") -> {
                    System.out.println(rooms[9].getFeature());
                    play.setSolved(false);
                    while (!play.getSolved()) {
                        uinput = text.nextLine();
                        if (uinput.contains("swat")) {
                            play.setSolved(true);
                        }
                        System.out.println((String) play.complete_puzzle(play.getSolved()));
                    }
                }
                // room 11
                case ("look glass") ->
                    System.out.println(rooms[10].getFeature());
                case ("get scythe") -> {
                    play.getInventory().removeItem("Scythe");
                    System.out.println("You have used the Scythe");
                    System.out.println("The glass is broken and the princess is free");
                    endgame = true;
                }
                case ("score") ->
                    System.out.println(play.getUscore().getScore());
                case ("move north") ->
                    System.out.println(play.moveNorth());
                case ("move south") ->
                    System.out.println(play.moveSouth());
                case ("move west") ->
                    System.out.println(play.moveWest());
                case ("move east") ->
                    System.out.println(play.moveEast());
                case ("inventory") ->
                    System.out.println(play.getInventory().displayInventory());
                case ("quit") ->
                    endgame = true;
                default -> {
                    play.help();
                    break;
                }
            }
            uinput = text.nextLine();
        }
play.endgame();
    }

    // initial output
    public void startGame() {
        tower.placeRoom(user, 'U'); // puts user on the map
        System.out.println("You are in The Tower and it is your duty to save the princess at the top of the tower, you must undergo a series of trials and tribulations in order to prove yourself worthy of the princess. \n PLAY");
        addRooms();
        System.out.println(tower.display());
    }

    public void endgame() {
        System.out.println("You have a score of " + this.getUscore().getScore());
        System.out.println("GAME OVER");
    }

    public Map getMap() {
        return tower;
    }

    public Inventory getInventory() {
        return utory;
    }

    public void setSolved(boolean right) {
        solved = right;
    }

    public boolean getSolved() {
        return solved;
    }

    public Score getUscore() {
        return uscore;
    }

    public boolean atroom() {
        // if user is at room location
        return user.x == rooms[roomsvisited].getPosition().x && user.y == rooms[roomsvisited].getPosition().y;
    }

    public void addRooms() {
        switch (roomsvisited) {
            case 0 -> {
                room = new Position(10, room.roomposition());
                rooms[0] = new Room("Dungeon", "The dark room was eerie with a slight draft that seemed to whistle through the cracks. With only a candle for warmth and light, the cold was nothing short of bone chilling.\n A cage sat in the back corner of the room and within it a faint hiss along with slight movement. With every sweep of air the candle within the cage seemed to flicker more, determined to give light despite the lack of life surround it with the walls laden with nothing more than slabs of corrugated stone.", 'D', room);
                tower.placeRoom(rooms[0].getPosition(), rooms[0].getSymbol());
                rooms[0].setFeature("The iron prison sat alone in the room in the only lit corner, its edges rusted and flaked, in the candlelight its brown trim had an almost golden glow. \nThe cage door was closed but held tight by an ancient lock. Its bars were twisted with just enough space between them for a pinky finger to fit through them.\n Despite the candle it seemed to be imprisoning the inside of the cage revealed nothing but swirling shadows. A serpent slithered over itself circling the candle but the cold blooded creature never ventured into the candles territory of light and warmth;\n so in the darkness resembled a swirling mass more than anything.");
                rooms[0].setItem("long stemmed key with a circular handle indicating a turret");
            }
            case 1 -> {
                room = new Position(9, room.roomposition());
                rooms[1] = new Room("Wine cellar", "Barrels line the walls either side of you; the ceiling vaulted with archways.", 'W', room);
                tower.placeRoom(rooms[1].getPosition(), rooms[1].getSymbol());
                rooms[1].setFeature("A drip echoes from an unattended running tap and beneath it a pool of sweet scented red. \nDrawn by the sweet smell of the wine and the desperation of thirst you are unable to take a taste that is unbeknownst to you is potent and with no food lining your stomach you quickly fall inebriated.");
                rooms[1].setItem("Brown bottle with a layer of dust showing its age, its label printed in black italics.");
            }
            case 2 -> {
                room = new Position(8, room.roomposition());
                rooms[2] = new Room("Steam Baths", "The whole room was marked by shades of brown with wooden tubs spread across the space in a diagonal line.\n Pails lay beside each tub and stools beside barrels topped with warm water.\n Steam circles though the room so thick it forms a fog immediately sweat begins to drip from your forehead and you are unable to see anything.", 'S', room);
                tower.placeRoom(rooms[2].getPosition(), rooms[2].getSymbol());
            }
            case 3 -> {
                room = new Position(7, room.roomposition());
                rooms[3] = new Room("Dinner Hall", "Benches are in rows and in the centre of the room is a pig on a spit twirling slowing over the lively flames. The noise rises and falls in between bites of the servs but otherwise a rumble remains.", 'H', room);
                tower.placeRoom(rooms[3].getPosition(), rooms[3].getSymbol());
                rooms[3].setFeature("Your stomach growls violently and youre quick to spot an empty seat and deposit yourself opposite a half empty plate with a spoon proceed to shovel food.\n All of sudden you feel your tongue swelling up and shortness breath, your throat is closing up and you feel faint. Night night. \n................ \nWhen you come too a round cook is sat beside your slumped body and the aftertaste of grass in your mouth. You are informed you were given a herbal remedy to mitigate your illnesses \nbut you still are unable to speak, the cooks extends her hand in request of payment.");
                rooms[3].setItem(" bottle with a layer of dust showing its age, its label printed in black italics.");
            }
            case 4 -> {
                room = new Position(6, room.roomposition());
                rooms[4] = new Room("Sleeping Chambers", "The guard room is a dilapidating stone prison with wooden cot beds sunken in and outlined with the bodies of their respective owners,\n a peg shaped window casts a shaft of light onto the runt of the beds in the centre and pushed against the cobblestone wall.", 'C', room);
                tower.placeRoom(rooms[4].getPosition(), rooms[4].getSymbol());
                rooms[4].setFeature("The bed has a wooden frame supported by wooden stilts and leans bidirectionally with the end of the bed facing towards the window on the right and the top of the frame leaning towards the left.\n The bed is like a smooth slab of yellowed stone with a bundle of cloth for a pillow.");

            }
            case 5 -> {
                room = new Position(5, room.roomposition());
                rooms[5] = new Room("Lesson Room", "A healthy fire burns in the heath; desks scatter the room each equipped with a candle the dripping wax eluding to how long the ladies have spent studying,\n their hands stained with ink. One of the ladies requests your help, how should she sign off her letter to her future.", 'L', room);
                tower.placeRoom(rooms[5].getPosition(), rooms[5].getSymbol());
            }
            case 6 -> {
                room = new Position(4, room.roomposition());
                rooms[6] = new Room("Bay Window", "A bay window outlined by wooden logs that frames a view of the sprawling forest beyond the tower. Vines weave through the cracks and logs along the window structure.", 'B', room);
                tower.placeRoom(rooms[6].getPosition(), rooms[6].getSymbol());
                rooms[6].setFeature("The vines are a vibrant green slithering along the window border.");
            }
            case 7 -> {
                room = new Position(3, room.roomposition());
                rooms[7] = new Room("Training Room", "Encompassing the arena were bright orange flags that in the wind resembled flickering flames, \nintricate images of warfare surrounded the cylinder walls with bronzed shields hoisted as ornate pieces.\n The top of the arena is made up of metal netting with smaller window size holes running the rim of the arena.\n In the centre of the room is a lone figure shrouded by the shadow of the waning day.\n In front of you is a sharpened scythe.", 'T', room);
                tower.placeRoom(rooms[7].getPosition(), rooms[7].getSymbol());
                rooms[7].setFeature("The figure is tall and broad, his eyes a bright red pointed directly at you.\n His stance is menacing, and his very position is threatening, he is blocking the exit.\n He stands between you and your escape and he will not let you pass, there is but one way to leave.\n Fight.");
                rooms[7].setItem("The scythe is held by a leather bound grip which feels supple in your grip \n its blade wide at the base near your hand that thins out into a singular point.");
            }
            case 8 -> {
                room = new Position(2, room.roomposition());
                rooms[8] = new Room("Laboratory", "Tiles line the floor with paved divots that run in parallel with running water flowing through.\n Two stone buckets are in the middle of room lolling with water. Wooden benches with holes in the middle line the perimeter of the room.", 'O', room);
                tower.placeRoom(rooms[8].getPosition(), rooms[8].getSymbol());
                rooms[8].setFeature("It has been a long journey and you have yet to relive yourself make sure you aim correct.");
            }
            case 9 -> {
                room = new Position(1, room.roomposition());
                rooms[9] = new Room("Turret", "Stone archways make up the turret. You have now reached the top of the tower\n  You must now save the princess, but flies are blocking your way.", 'R', room);
                tower.placeRoom(rooms[9].getPosition(), rooms[9].getSymbol());
                rooms[9].setFeature("Remove the flies in the way so you can get to the princess");
            }
            case 10 -> {
                room = new Position(0, room.roomposition());
                rooms[10] = new Room("Glass Box", "You have finally made it! Your princess awaits. There she is so beautiful in her glass box, just waiting for you to save her.\\n She is iridescent in the light of her decanter cage. ", 'G', room);
                tower.placeRoom(rooms[10].getPosition(), rooms[10].getSymbol());
                rooms[10].setFeature("The glass box has swirling ice crystals that almost entirely shield the princess from view save for her peaceful face. But you need to break the glass the free her.");
            }
            default -> {
            }
        }
    }

    public String look() {
        if (atroom()) // if user is at room location 
        {
            uscore.visitRoom();
            roomsvisited++;
            addRooms();
            return rooms[roomsvisited-1].getDescription();
        }
        return "You've not reached the room yet, keep going";

    }

    public String help() {
        return "• look - Displays a description of the room the player is in. \n • look <feature> - Displays a more detailed description of a feature of a room.\n • look <item> - Displays a description of an item. \n • inventory - Displays a list of all items the player has obtained. \n • score - Displays the user’s current score.\n • map - Displays a text-based map of the current explored game world.\n • help - Displays a help message.\n• quit - Quits the game.";
    }

    public String moveNorth() {
        tower.placeRoom(user, '.');// replaces the user location 
        if (!tower.outOfBounds(user.x - 1, user.y)) {
            user.x += -1;
            tower.placeRoom(user, 'U');
            return tower.display();
        } else {
            return "try a different move";
        }
    }

    public String moveSouth() {
        tower.placeRoom(user, '.');
        if (!tower.outOfBounds(user.x + 1, user.y)) {
            user.x += 1;
            tower.placeRoom(user, 'U');
            return tower.display();
        } else {
            return "try a different move";
        }
    }

    public String moveEast() {
        tower.placeRoom(user, '.');
        if (!tower.outOfBounds(user.x, user.y + 1)) {
            user.y += 1;
            tower.placeRoom(user, 'U');
            return tower.display();
        } else {
            return "try a different move";
        }
    }

    public String moveWest() {
        tower.placeRoom(user, '.');
        if (!tower.outOfBounds(user.x, user.y - 1)) {
            user.y += -1;
            tower.placeRoom(user, 'U');
            return tower.display();
        } else {
            return "try a different move";
        }
    }

    public String complete_puzzle(boolean correct) {
        if (correct) {
            uscore.solvePuzzle();
            return "well done";
        }
        return "that's not quite right";
    }
}
