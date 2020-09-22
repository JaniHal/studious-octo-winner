using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LapUI : MonoBehaviour
{

    Text text;
    public static int LapCurrent;
    public static int LapMax;

    void Awake()
    {

        LapMax = GameObject.Find("CheckpointParent").GetComponent<RaceController>().lapsInRace;
        text = GetComponent<Text>();
    }

    // Update is called once per frame
    void Update()
    {
        LapCurrent = GameObject.Find("CheckpointParent").GetComponent<RaceController>().lapCount;
        text.text = "Lap: " + LapCurrent + "/" + LapMax;
    }
}
