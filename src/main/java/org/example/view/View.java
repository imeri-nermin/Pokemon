package org.example.view;

import org.example.model.Attack;
import org.example.controller.Controller;
import org.example.model.Pokemon;

import java.util.Scanner;

public class View {
    private final Controller controller;

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
        System.out.println();
        String userInput = scanner.nextLine();
        Pokemon playerPokemon = controller.findPokemon(userInput);
        if (playerPokemon == null) {
            System.out.println("Invalid Pokémon selection. Exiting game.");
            return;
        }
        System.out.println("You've chosen " + playerPokemon.getName() + " Level " + playerPokemon.getLevel() + "!");
        displayAvailableAttacks(playerPokemon);

        Pokemon opponentPokemon = controller.selectOpponentPokemon();
        System.out.println("Your Opponent chose " + opponentPokemon.getName() + " Level " + opponentPokemon.getLevel() + "!");
        displayAvailableAttacks(opponentPokemon);

        Pokemon attacker;
        Pokemon defender;
        if (playerPokemon.getSpeed() >= opponentPokemon.getSpeed()) {
            attacker = playerPokemon;
            defender = opponentPokemon;
        } else {
            attacker = opponentPokemon;
            defender = playerPokemon;
        }

        while (playerPokemon.getHP() > 0 && opponentPokemon.getHP() > 0) {
            Attack selectedAttack;
            if (attacker == playerPokemon) {
                selectedAttack = this.getPlayerAttack(playerPokemon);
            } else {
                selectedAttack = controller.selectRandomAttack(opponentPokemon);
            }

            attackPokemon(attacker, defender, selectedAttack);

            if (defender.getHP() <= 0) {
                System.out.println(defender.getName() + " fainted! " + attacker.getName() + " wins!");
                break;
            }

            Pokemon temp = attacker;
            attacker = defender;
            defender = temp;
        }
    }
    private void attackPokemon(Pokemon attacker, Pokemon defender, Attack attack) {
        displayPokemonStatus(attacker);

        int damage = (int) controller.calculateDamage(attack, attacker, defender);
        defender.reduceHP(damage);
        System.out.println(attacker.getName() + " used " + attack.getName() + "! It dealt " + String.format("%d", damage) + " damage to " + defender.getName() + "!");
    }

    private Attack getPlayerAttack(Pokemon playerPokemon) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(playerPokemon.getName() + ", choose an attack (1 or 2): ");
        displayAvailableAttacks(playerPokemon);
        System.out.println();
        //TODO: Force the user to select 1 or 2!
        int attackChoice = scanner.nextInt();
        scanner.nextLine();

        return  (attackChoice == 1) ? playerPokemon.getAttack1() : playerPokemon.getAttack2();
    }

    private void displayPokemonStatus(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " HP: " + pokemon.getHP());
    }

    private void displayAvailableAttacks(Pokemon pokemon) {
        System.out.println("Available Attacks:");
        System.out.println("1. " + pokemon.getAttack1().getName());
        System.out.println("2. " + pokemon.getAttack2().getName());
    }
}
