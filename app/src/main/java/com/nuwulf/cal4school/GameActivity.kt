package com.nuwulf.cal4school

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_events.*
import java.lang.IndexOutOfBoundsException
import java.util.logging.Logger

class GameActivity : AppCompatActivity() {
    /*
    B C D
    A E F
    G H I
     */
    private var A:ArrayList<Int> = ArrayList()
    private var B:ArrayList<Int> = ArrayList()
    private var C:ArrayList<Int> = ArrayList()
    private var D:ArrayList<Int> = ArrayList()
    private var E:Int = -1
    private var F:Int = -1
    private var G:Int = -1
    private var H:Int = -1
    private var I:Int = -1
    private var count:Int = 0
    private var points:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        firstStep()
    }
    private fun randomizer():ArrayList<Int>{
        val shuffledDeck = (1..54).shuffled()
        val newArray:ArrayList<Int> = ArrayList()
        for(x in shuffledDeck){
            newArray.add(x)
        }
        return newArray
    }
    private fun firstStep(){
        D = randomizer()
        G = D[0]; D.remove(G)
        H = D[0]; D.remove(H)
        I = D[0]; D.remove(I)
    }
    private fun discard(temp:Array<Int>){
        for(x in temp){
            when (count) {
                0 -> A.add(x)
                1 -> B.add(x)
                2 -> C.add(x)
            }; count++
            if(count==3){count=0}
        }
    }
    private fun reshuffle(){
        discard(arrayOf(G, H, I))
        var successs = false
        G = -1; H = -1; I = -1
        while(!successs)
            try{
                if(G!=-1){G = D[0]; D.remove(G)}
                if(H!=-1){H = D[0]; D.remove(H)}
                if(I!=-1){I = D[0]; D.remove(I)}
                successs = true
            } catch (e:IndexOutOfBoundsException){
                restock()
            }
    }
    private fun restock(){
        for(z in C){D.add(z); C.remove(z)}
        for(x in A){D.add(x); A.remove(x)}
        for(y in B){D.add(y); B.remove(y)}
    }
    private fun pointGiver(num:Int):Int{
        return when(num){
            1 -> 25
            2,3,4,5,6,7,8,9,10 -> 16-num
            11,12,13 -> 14-num
            else -> 0
        }
    }
    private fun play(card:Int){
        E = card
        var success1 = false
        while(!success1){
            try{
                F = D[0]
                success1 = true
            } catch (e:IndexOutOfBoundsException){
                restock()
            }
        }
        compare()
        var success2 = false
        while(!success2){
            try{
                when(card){
                    G -> G = D[0]
                    H -> H = D[0]
                    I -> I = D[0]
                }
                success2 = true
            } catch (e:IndexOutOfBoundsException){
                restock()
            }
        }
    }
    private fun convertToCards(card:Int):Int{
        return when {
            card>=53 -> 0
            card>=40 -> card-39
            card>=27 -> card-26
            card>=14 -> card-13
            else -> card
        }
    }
    private fun compare(){
        val comE = convertToCards(E)
        val comF = convertToCards(F)
        if(comE or comF == 0){
            if(comE == comF){
                points+=50
                reshuffle()
            } else {
                reshuffle()
            }
        } else {
            when {
                comE > comF -> { pointGiver(comE); discard(arrayOf(E,F)) }
                comE < comF -> discard(arrayOf(E, F))
                else -> { endGame() }
            }
        }
    }
    private fun endGame(){
    }
    private fun graphics(cards:Int, graphRow:Int):String{
        return when(cards){
            0 -> {
                when(graphRow){
                    0 -> "╭┬┰╮"
                    1 -> "├╆┫│"
                    2 -> "├╊┛│"
                    3 -> "╰┸─╯"
                    else -> "Err!"
                }
            }
            1 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ A│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            2 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 2│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            3 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 3│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            4 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 4│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            5 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 5│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            6 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 6│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            7 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 7│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            8 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 8│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            9 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ 9│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            10-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│10│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            11-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ J│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            12-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ Q│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            13-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♠ │"
                    2 -> "│ K│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            14 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ A│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            15 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 2│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            16 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 3│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            17 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 4│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            18 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 5│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            19 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 6│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            20 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 7│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            21 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 8│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            22 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ 9│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            23 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│10│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            24 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ J│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            25 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ Q│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            26 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♣ │"
                    2 -> "│ K│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            27-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ A│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            28-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 2│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            29-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 3│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            30-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 4│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            31-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 5│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            32-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 6│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            33-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 7│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            34-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 8│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            35-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ 9│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            36-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│10│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            37-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ J│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            38-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ Q│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            39-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♥ │"
                    2 -> "│ K│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            40 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ A│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            41 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 2│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            42 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 3│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            43 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 4│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            44 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 5│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            45 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 6│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            46 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 7│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            47 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 8│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            48 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ 9│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            49 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│10│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            50 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ J│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            51 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ Q│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            52 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♦ │"
                    2 -> "│ K│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            53-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♪J│"
                    2 -> "│Kr│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            54-> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│♫J│"
                    2 -> "│Kr│"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            55 -> {
                when(graphRow){
                    0 -> "╭──╮"
                    1 -> "│  │"
                    2 -> "│  │"
                    3 -> "╰──╯"
                    else -> "Err!"
                }
            }
            else -> {
                when(graphRow){
                    0 -> "╭┄┄╮"
                    1 -> "┊  ┊"
                    2 -> "┊  ┊"
                    3 -> "╰┄┄╯"
                    else -> "Err!"
                }
            }
        }
    }
}