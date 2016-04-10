package Litholode.src.Litholode;
import java.util.Scanner;

public class Driver {
    public static void main (String[] args){
        Scanner scan= new Scanner(System.in);
        Place[][] gameboard =new Place[5][10];
        String name ="";
        String description = "";
        boolean running = true;
        int depth = 0;
        boolean win = false;
        Item treasure=null;
        Enemy enemies=null;
        Mineral diamond = new Mineral("Diamond", "Taken from the dead fingers of your mummy.", 100, 8);
        Mineral emerald = new Mineral("Emerald", "A dull green luster can be seen glimmering in the dim light.", 50, 7);
        Mineral copper = new Mineral("Copper", "From an old mineshaft.", 10, 3);
        Mineral gold = new Mineral("Gold", "The shiny golden metal.", 20, 2);
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Litholode. You are a mining craft, stuck on a distant planet looking for resources, and a way out.\nTo escape, find an Ancient Key, lost in the caverns deep below, that has the capabilities to reactivate your flagship.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\n");
        for(int r = 0;r< gameboard.length; r++){
            for(int c=0; c < gameboard[r].length;c++){
                int x = (int) (Math.random() * 30);
                treasure=null;
                enemies=null;
                                if (x >=0 && x<= 4){
                    name = "Empty Cave";
                    description = "There's nothing here. Better move on.";
                }
                
                
                if (x>=4&&x<=6){
                    name = "Alien City";
                    description = "Dark, abandoned city previously inhabited by aliens.";
                    treasure = emerald;
                }
                if (x>=7&&x<=10){
                    name = "Collapsed Mineshaft";
                    description = "A swarm of bats surround you, then flutter away screeching. You escaped easily this time";
                    treasure = copper;
                }
                
                
                if (x>=11&&x<=13){
                    name = "Lava Cavern";
                    description = "Lava drips from the ceiling, sizzling as it splatters against the ground.";
                    enemies = new Enemy("a Lava Troll", "His pulsating body exudes molten lava", 7);
                    enemies.forceaddItems(emerald);
                }
                
                
                if (x>=14&&x<=15){
                    name = "Glowing Mushroom Cave";
                    description = "The iridescence of the mushrooms overpowers your reflector interceptors.";
                    enemies = new Enemy("Dan", "The Glowing Mushroom Man", 7);
                    enemies.forceaddItems(gold);
                    treasure = new Mineral("Sapphire", "Shimmers in the faint light emitted by the glowing mushrooms.", 40, 6);
                }
                if (x>=16&&x<=19){
                    name = "Pithole";
                    description = "You stumble and fall through the loose earth into a pithole.";
                    enemies = new Enemy("a Pitmonster", "His pits reek.", 5);
                    treasure = new Mineral("Bismuth", "A beam from your reflectors illuminates the partially hidden Bismuth.", 15, 3 );
                }
                if (x>=20&&x<=23){
                    name = "Quicksand";
                    description = "You slip into well hidden patch of quicksand and are sucked under.";
                    enemies = new Enemy("the Sandman", "A mass of dripping sand and dirt.", 5);
                    enemies.forceaddItems(copper);
                    treasure = gold;
                }
                if (x>=24&&x<=27){
                    name = "Underground Jungle";
                    description = "Massive trees envelop you as you move into the cavern.";
                    enemies = new Enemy("a group of Undead Monkeys", "Their skeletal limbs cling to branches high above and their screeches pierce your ears.", 3);
                    treasure = new Mineral("Silver", "Immersed in the roots of the huger trees, you catch sight of gleaming silver.", 15, 4);
                }
                if (x==28){
                    name = "Spooky Crypt";
                    description = "You break through the wall of a long-forgotten crypt. What waits within?";
                    enemies = new Enemy("Mummy", "Don't kill your mummy.", 9);
                    treasure = diamond;
                }
                if (x==29){
                    name = "Submerged Pool";
                    description = "You break a slab of stone and are swept into a large underground lake.";
                    enemies = new Enemy("a Leviathan", "A high roar echoes throughout the cavern as a large scaled head emerges.", 9);
                    enemies.forceaddItems(diamond);
                    treasure = new Mineral("Sapphire", "You nearly miss the pockets of sapphire that lie submerged beneath the dark waters of the underground lake.", 40, 6);
                }
                if (x >=0 && x<= 4)
                    gameboard[r][c] = new Place (name, description, false, r, c);
                else if (x>=4&&x<=10)
                    gameboard[r][c] = new Place (name, description, treasure, false, r, c);
                else if (x>=11&&x<=13)
                gameboard[r][c] = new Place (name, description, enemies, false, r, c);
                else gameboard[r][c] = new Place (name, description, treasure, enemies, false, r, c);
            }
        }
        Place ship=new Place("Stranded Flagship", "A way home", true, 0, 0);
        gameboard [0][0] = ship;
        AncientKey a = new AncientKey("Key", "The way out", 10);
        Place temple = new Place("Ancient Temple", "A holy temple, eons old. How did the key end buried here? In such tough stone too.", a, false, ((int)Math.random() *5), ((int)Math.random() *5 + 5));
        gameboard[temple.getXCoordinate()][temple.getYCoordinate()] = temple;
        int destination = 0;
        System.out.print("Enter a name: ");
        String tempname=(scan.nextLine());
        System.out.print("\n");
        System.out.print("Enter a title: ");
        String tempdesc=(scan.nextLine());
        System.out.print("\n");
        Player run = new Player(tempname, tempdesc, 5, 10, ship);
        
        System.out.println("-------------------------------------------------");
        while (run.getAliveStatus()){
            System.out.println(run.getStatus());
            if (run.getLocation().enemyexists()){
                System.out.println("You are confronted by " + run.getLocation().getEnemy().toString());
            }
            if (run.getLocation().itemexists()){
                System.out.println("This area is rich with " + run.getLocation().getItem().toString());
            }
            if (run.getLocation().isitShip()){
            System.out.println("Enter the number of the action you want to take:\n1. Move\n2. Shop\n3. Refill Fuel\n4. Sell Minerals");
            int temp = scan.nextInt();
            System.out.println("-------------------------------------------------");
            switch (temp){
                case 1:        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
                if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==0){
                       down = false;

                       }
                       if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==9){
                               up = false;

                           }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==0){
                                   left = false;

                               }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==4){
                                        right = false;
                                   }
                       System.out.println("Enter the number of the action of where you want to go:\n1. Up\n2. Down\n3. Left\n4. Right");
                       destination = scan.nextInt();
                       System.out.println("-------------------------------------------------");
                       switch (destination){
                       case 1: if (up == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()+1]);
                                    }
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       case 2: if (down == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()-1]);
                                    }
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       case 3: if (left == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()-1][run.getLocation().getYCoordinate()]);
                                    }
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       case 4: if (right == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()+1][run.getLocation().getYCoordinate()]);
                                    }
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       default: System.out.println("Invalid input.");
                       System.out.println("-------------------------------------------------");
                       break;
                        }
                       break;
                case 2:System.out.println("Enter the number of what you would like to buy:\n1. Cargo Upgrade\n2. Drill Upgrade\n3. Fueltank Upgrade\n4. Reactivate Flagship");
                int purchase = scan.nextInt();
                System.out.println("-------------------------------------------------");
                    switch (purchase){
                        case 1: if(run.getCash()>=10){
                                run.upgradeInventory(5);
                                System.out.println("Inventory upgraded");
                                run.subtractCash(10);
                            }
                                else System.out.println("Too poor to purchase.");
                                    System.out.println("-------------------------------------------------");
                                break;
                        case 2: if(run.getCash()>=15){
                                run.upgradeDrill();
                                System.out.println("Drill upgraded");
                                run.subtractCash(15);

                            }
                                else System.out.println("Too poor to purchase.");
                                    System.out.println("-------------------------------------------------");
                                break;
                        case 3: if(run.getCash()>=20){
                        run.upgradeFuel();
                        System.out.println("Fueltank upgraded");
                        run.subtractCash(20);
                    }
                    else System.out.println("Too poor to purchase.");
                        System.out.println("-------------------------------------------------");
                        break;
                        case 4: if(run.hasKey()){
                            win = true;
                            run.setAliveStatus(false);
                        }
                        else System.out.println("You do not have the key.");
                            System.out.println("-------------------------------------------------");
                                break;
                        default: System.out.println("Invalid input.");
                        System.out.println("-------------------------------------------------");
                        break;
                    }
                    break;
                case 3: run.refillFuel();
                break;
                case 4: run.sellItems(run);
                break;
                default: System.out.println("Invalid input.");
                System.out.println("-------------------------------------------------");
                break;
            }
            
            
            
        }
        
        else if (run.getLocation().enemyexists()&&run.getLocation().itemexists()){
            System.out.println("Enter the number of the action you want to take:\n1. Move\n2. Battle\n3. Mine");
            int temp = scan.nextInt();
            System.out.println("-------------------------------------------------");
            switch (temp){
                case 1:        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
                if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==0){
                       down = false;

                       }
                       if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==9){
                               up = false;

                           }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==0){
                                   left = false;

                               }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==4){
                                        right = false;
                                   }
                       System.out.println("Enter the number of the action of where you want to go:\n1. Up\n2. Down\n3. Left\n4. Right");
                       destination = scan.nextInt();
                       System.out.println("-------------------------------------------------");
                       switch (destination){
                       case 1: if (up == true)
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()+1]);
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}
                              break;
                       case 2: if (down == true)
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()-1]);
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}
                              break;
                       case 3: if (left == true)
                                    run.move(gameboard[run.getLocation().getXCoordinate()-1][run.getLocation().getYCoordinate()]);
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}    
                              break;
                       case 4: if (right == true)
                                    run.move(gameboard[run.getLocation().getXCoordinate()+1][run.getLocation().getYCoordinate()]);
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}   
                              break;
                       default: System.out.println("Invalid input.");
                       System.out.println("-------------------------------------------------");
                       break;
                        }
                       break;
                case 2: run.battle(run.getLocation().getEnemy());
                break;
                case 3: run.mine(run.getLocation().getItem());
                break;
                default: System.out.println("Invalid input.");
                System.out.println("-------------------------------------------------");
                break;
            }
        
       }
       else if (run.getLocation().enemyexists()){
            System.out.println("Enter the number of the action you want to take:\n1. Move\n2. Battle");
            int temp = scan.nextInt();
            System.out.println("-------------------------------------------------");
            switch (temp){
                case 1:        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
                if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==0){
                       down = false;

                       }
                       if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==9){
                               up = false;

                           }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==0){
                                   left = false;

                               }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==4){
                                        right = false;
                                   }
                       System.out.println("Enter the number of the action of where you want to go:\n1. Up\n2. Down\n3. Left\n4. Right");
                       destination = scan.nextInt();
                       System.out.println("-------------------------------------------------");
                       switch (destination){
                       case 1: if (up == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()+1]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}
                              break;
                       case 2: if (down == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()-1]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}   
                              break;
                       case 3: if (left == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()-1][run.getLocation().getYCoordinate()]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}   
                              break;
                       case 4: if (right == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()+1][run.getLocation().getYCoordinate()]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}    
                              break;
                       default: System.out.println("Invalid input.");
                       System.out.println("-------------------------------------------------");
                       break;
                        }
                       break;
                case 2: run.battle(run.getLocation().getEnemy());
                break;
                default: System.out.println("Invalid input.");
                System.out.println("-------------------------------------------------");
                break;
            }
        
       }
               else if (run.getLocation().itemexists()){
            System.out.println("Enter the number of the action you want to take:\n1. Move\n2. Mine");
            int temp = scan.nextInt();
            System.out.println("-------------------------------------------------");
            switch (temp){
                case 1:        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
                if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==0){
                       down = false;

                       }
                       if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==9){
                               up = false;

                           }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==0){
                                   left = false;

                               }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==4){
                                        right = false;
                                   }
                       System.out.println("Enter the number of the action of where you want to go:\n1. Up\n2. Down\n3. Left\n4. Right");
                       destination = scan.nextInt();
                       System.out.println("-------------------------------------------------");
                       switch (destination){
                       case 1: if (up == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()+1]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}   
                              break;
                       case 2: if (down == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()-1]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}    
                              break;
                       case 3: if (left == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()-1][run.getLocation().getYCoordinate()]);
                                    System.out.println(run.getStatus());}
                                else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}   
                              break;
                       case 4: if (right == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()+1][run.getLocation().getYCoordinate()]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}    
                              break;
                       default: System.out.println("Invalid input.");
                       System.out.println("-------------------------------------------------");
                       break;
                        }
                       break;
                case 2: run.mine(run.getLocation().getItem());
                break;
                default: System.out.println("Invalid input.");
                System.out.println("-------------------------------------------------");
                break;
            }
        }
            else 
            {boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
                if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==0){
                       down = false;

                       }
                       if (run.getLocation().getXCoordinate()>=0&&run.getLocation().getXCoordinate()<=4&&run.getLocation().getYCoordinate()==9){
                               up = false;

                           }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==0){
                                   left = false;

                               }
                       if (run.getLocation().getYCoordinate()>=0&&run.getLocation().getYCoordinate()<=9&&run.getLocation().getXCoordinate()==4){
                                        right = false;
                                   }
                       System.out.println("Enter the number of the action of where you want to go:\n1. Up\n2. Down\n3. Left\n4. Right");
                       destination = scan.nextInt();
                       System.out.println("-------------------------------------------------");
                       switch (destination){
                       case 1: if (up == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()+1]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       case 2: if (down == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()][run.getLocation().getYCoordinate()-1]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       case 3: if (left == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()-1][run.getLocation().getYCoordinate()]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       case 4: if (right == true){
                                    run.move(gameboard[run.getLocation().getXCoordinate()+1][run.getLocation().getYCoordinate()]);
                                    System.out.println(run.getStatus());}
                               else{ System.out.println("Invalid movement."); 
                                   System.out.println("-------------------------------------------------");}  
                              break;
                       default: System.out.println("Invalid input.");
                       System.out.println("-------------------------------------------------");
                       break;
                    }
       }
    
    }
       if(win){
           System.out.println(run.getName() + " left the planet, never to return again. You win!");
        }
    }
}