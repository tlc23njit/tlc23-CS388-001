Ideas:
 - Trivia: Jeopardy board with points scoring system, and a way to add your own trivia questions (most of them come from an online api to be found later)
 - Idle Game: Some sort of farming sim game, where you grow your farm and harvest crops and tend to live stock
 - Vacation Generator: Take an api of random places around the world, store abunch of random facts about them, and use gps system to find a way there using a variety of methods (boat, car, plane, walk) and measure the time and cost of those
 - To do / reminder app: reminds user of plans and commitments
 - Food Nutrient tracker and finder: input what foods/vitamins/drinks you consumed, and the app will show what nutrients you had and what you need. It will also show food recommendations for nutrients you need/food you want and show you how to make it
 - Deal Finder: basically something similar to amazon, but it will look at the deals across a few ecommerce websites and see which one offers the best deals
Evaluating Ideas:
 - Trivia: App would be easier/more conveniet than using a website
    - would have both push, audio, real time
    - not super unique
    - I would use this app with my friends and family
    - We love trivia and this would make it easier to do
    - Potentially pretty big user base for the game portion and a much smaller one for the generating trivia questions
    - In terms of audience or niche, I don't think we have one
    - This idea is just mobile kahoot kind of
    - In terms of scope, I feel like I know exactly what I need to do (for the most part) to get this done.
    - We used a trivia api in the past for an extra credit assignment, so apis are avaliable
 - Vacation Generator: Fun diea
    - would have location, push, maps, audio, realtime
    - Also not super unique (easy google searchs or something)
    - I personally won't use this
    - Could be helpful to some people and entertaining for others (Some people just like to see what's out there)
    - Very niche product I would say
    - Not super sure who would be target audienc
    - I don't know how to use map apis and a lot of other things that has to do with this project other than randomly finding locations
 - Deal Finder
    - Would have push, audio, realtime
    - Very useful and usable by a lot of people
    - I don't personally want to do this project
    - I've made a web version of this before using webscraping in python and node.js
    - No idea how to translate that stuff to kotlin though

I choose Trivia. It was my original idea, and I love doing trivia with my family, and so far it's been a real pain to set up. Now I could use this app to help me do it, and since I'm making it, it will have everything I could want in a trivia app

Design Specs:
 - Required:
    - Trivia question database
    - Jeopardy Board RecyclerView/List
    - Upload questions to database
    - Each Question needs their own screen that shows both question, answer, and points
    - Teams point system
 - Optional:
    - Internet play
    - Sound Affects
    - Final Jeopardy / Daily Double Randomizer
    - Match History
 - Screen Archetypes
    - Fragments for Jeopardy Board (Stream), Submit Question (Creation), Team(s) selection/Game Set up (Creation/Settings)
    - DetailActivity for each question (Detail)
 - Flow Navigation
    - 3+ Activity Screens
       - Main Menu (Takes you to either Game Set up/Question Creation)
       - Game Set up (Leads to In Game Jeopardy Board)
       - Question Creation
       - In Game Jeopardy Board
         - Tabs: Game Board and Team Points
     - DetailActivities
       - Each Question gets their own screen
  
