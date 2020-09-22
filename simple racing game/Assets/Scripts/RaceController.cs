using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class RaceController : MonoBehaviour
{
    public int lapsInRace;

    private int nextCheckpointNumber;
    public int checkpointCount;
    public int lapCount;
    public float raceTotalTime = 0.0f;
    public float fastestLapTime;
    private float lapStartTime;
    public GameObject Canvas;
    public Text text;
    // Laptimes get stored in a list
    // Kierrosajat tallennetaan listaan
    private List<float> lapTimes = new List<float>();
    private Checkpoint activeCheckpoint;

    // Use this for initialization
    void Start()
    {
        Canvas.gameObject.SetActive(false);
        nextCheckpointNumber = 0;
        lapCount = 0;
        checkpointCount = this.transform.childCount;
        // Assign each of the checkpoints its own number in order in Hierarchy
        // Annetaan jokaiselle tarkastuspisteelle sen järjestysnumero hierarkian järjestyksen mukaan
        for (int i = 0; i < checkpointCount; i++)
        {
            Checkpoint cp = transform.GetChild(i).GetComponent<Checkpoint>();
            cp.checkpointNumber = i;
            cp.isActiveCheckpoint = false;
        }
        StartRace();
    }

    // Update is called once per frame
    void Update()
    {

    }

    public void StartRace()
    {
        // Set the first checkpoint as the active one
        // Asetetaan ensimmäinen tarkastuspiste aktiiviseksi
        activeCheckpoint = transform.GetChild(nextCheckpointNumber).GetComponent<Checkpoint>();
        activeCheckpoint.isActiveCheckpoint = true;
        lapStartTime = Time.time;
    }

    public void CheckpointPassed()
    {
        // A checkpoint was passed, so we make it inactive and activate the next one
        // Tarkastuspisteen läpi ajettiin, joten epäaktovoimme sen ja teemme seuraavasta aktiivisen
        activeCheckpoint.isActiveCheckpoint = false;
        nextCheckpointNumber++;
        if (nextCheckpointNumber < checkpointCount)
        {
            activeCheckpoint = transform.GetChild(nextCheckpointNumber).GetComponent<Checkpoint>();
            activeCheckpoint.isActiveCheckpoint = true;

        }
        // If a lap was finished, we enter the new lap, and the checkpoint-counter is reset
        // Jos kierros loppui, mennään seuraavalle kierrokselle ja nollataan tarkastuspistelaskuri
        else

        {
            // Add the laptime to the list of laptimes
            // Lisätään kierrosaika kierrosaikojen listaan
            lapTimes.Add(Time.time - lapStartTime);
            lapCount++;
            // Reset the lap timer
            // Nollataan kierrosaika
            lapStartTime = Time.time;
            nextCheckpointNumber = 0;



            // If the finished lap wasn't the last lap
            // Jos päätetty kierros ei ollut viimeinen kierros
            if (lapCount < lapsInRace)
            {
                activeCheckpoint = transform.GetChild(nextCheckpointNumber).GetComponent<Checkpoint>();
                activeCheckpoint.isActiveCheckpoint = true;
            }
            // If final lap, end the game and calculate results
            // Jos viimeinen kierros, päätä peli ja laske tulokset
            else
            {
                
                fastestLapTime = lapTimes[0];
                for (int i = 0; i < lapsInRace; i++)
                {
                    
                    // Compare the laptimes to pick fastest
                    // Vertaa kierrosaikoja nopeimman löytämiseksi
                    if (lapTimes[i] < fastestLapTime)
                    {
                        fastestLapTime = lapTimes[i];
                    }
                    // Count total time
                    // Laske kokonaisaika
                    raceTotalTime = raceTotalTime+lapTimes[i];
                    Debug.Log(lapTimes[0]);
                    Debug.Log(lapTimes[1]);


                }
                Canvas.gameObject.SetActive(true);
                text.text = "Total time: " + raceTotalTime + "\n Fastest lap: " + fastestLapTime;

            }
        }
    }
}