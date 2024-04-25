package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pokémon Battle Simulation!");

        System.out.println("Available Pokémon:");
        for (Pokemon pokemon : controller.getPokemonList()) {
            System.out.println(pokemon.getId() + ". " + pokemon.getName());
        }

        System.out.print("Choose a Pokémon (enter ID or name): ");
        String userInput = scanner.nextLine();
        Pokemon playerPokemon = findPokemon(userInput);
        if (playerPokemon == null) {
            System.out.println("Invalid Pokémon selection. Exiting game.");
            return;
        }
        System.out.println("You've chosen " + playerPokemon.getName() +" Level "+ playerPokemon.getLevel()+"!");
        System.out.println("Available Attacks:");
        System.out.println("1. " + playerPokemon.getAttack1().getName());
        System.out.println("2. " + playerPokemon.getAttack2().getName());

        Pokemon opponentPokemon = selectOpponentPokemon();
        System.out.println("Your Opponent chose " + opponentPokemon.getName() +" Level "+ opponentPokemon.getLevel()+"!");
        System.out.println("Available Attacks:");
        System.out.println("1. " + opponentPokemon.getAttack1().getName());
        System.out.println("2. " + opponentPokemon.getAttack2().getName());

        while (playerPokemon.getHP() > 0 && opponentPokemon.getHP() > 0) {
            displayPokemonStatus(playerPokemon);
            displayPokemonStatus(opponentPokemon);
            displayAvailableAttacks(playerPokemon);

            System.out.print("Choose an attack (1 or 2): ");
            int attackChoice = scanner.nextInt();
            scanner.nextLine();

            Attack selectedAttack = (attackChoice == 1) ? playerPokemon.getAttack1() : playerPokemon.getAttack2();
            double damage = controller.calculateDamage(selectedAttack, playerPokemon, opponentPokemon);
            opponentPokemon.reduceHP(damage);
            System.out.println(playerPokemon.getName() + " used " + selectedAttack.getName() + "! It dealt " + damage + " damage to " + opponentPokemon.getName() + "!");

            if (opponentPokemon.getHP() <= 0) {
                System.out.println(opponentPokemon.getName() + " fainted! You win!");
                break;
            }

            Attack opponentAttack = selectRandomAttack(opponentPokemon);
            damage = controller.calculateDamage(opponentAttack, opponentPokemon, playerPokemon);
            playerPokemon.reduceHP(damage);
            System.out.println(opponentPokemon.getName() + " used " + opponentAttack.getName() + "! It dealt " + damage + " damage to " + playerPokemon.getName() + "!");

            if (playerPokemon.getHP() <= 0) {
                System.out.println(playerPokemon.getName() + " fainted! You lose!");
                break;
            }
        }
    }


            private Pokemon findPokemon (String userInput){
                List<Pokemon> pokemonList = controller.getPokemonList();
                for (Pokemon pokemon : pokemonList) {
                    if (String.valueOf(pokemon.getId()).equals(userInput) || pokemon.getName().equalsIgnoreCase(userInput)) {
                        return pokemon;
                    }
                }
                return null;
            }

            private Pokemon selectOpponentPokemon () {
                List<Pokemon> pokemonList = controller.getPokemonList();
                Random random = new Random();
                return pokemonList.get(random.nextInt(pokemonList.size()));
            }

            private Attack selectRandomAttack (Pokemon pokemon){
                Random random = new Random();
                return (random.nextBoolean()) ? pokemon.getAttack1() : pokemon.getAttack2();
            }

            private void displayPokemonStatus (Pokemon pokemon){
                System.out.println(pokemon.getName() + " HP: " + pokemon.getHP());
            }

            private void displayAvailableAttacks (Pokemon pokemon){
                System.out.println("Available Attacks:");
                System.out.println("1. " + pokemon.getAttack1().getName());
                System.out.println("2. " + pokemon.getAttack2().getName());
            }
        }
