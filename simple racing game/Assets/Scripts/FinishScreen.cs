/*using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class FinishScreen : MonoBehaviour
{

    Text text;
    public static float laptime;
    public static float fastestlap;

    void Awake()
    {

        
    }

    // Update is called once per frame
    void Update()
    {
        laptime = GameObject.Find("StartingScene").GetComponent<RaceController>().raceTotalTime;
        fastestlap = GameObject.Find("Background").GetComponent<RaceController>().fastestLapTime;
        text.text = "Total time: " + laptime + "\n Fastest lap: " + fastestlap;
        Debug.Log(laptime);
        Debug.Log(fastestlap);
    }
}*/
