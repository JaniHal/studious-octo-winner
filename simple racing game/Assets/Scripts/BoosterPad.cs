using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BoosterPad : MonoBehaviour
{
    public GameObject PlayerShip;
    [SerializeField]
    private float turboAmount;
    [SerializeField]
    private float turboDuration;
    private float lastTurboAt;
    private bool isBoosting;
    void Start()
    {
        lastTurboAt = -turboDuration;
    }

    void FixedUpdate()
    {
        if (Time.time - lastTurboAt < turboDuration)
        {
            PlayerShip.GetComponent<Rigidbody>().AddForce(transform.forward * turboAmount);
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (Time.time - lastTurboAt >= turboDuration)
        {
            if (other.tag == "Player")
            {
                isBoosting = true;
                lastTurboAt = Time.time;
            }
        }
    }
}