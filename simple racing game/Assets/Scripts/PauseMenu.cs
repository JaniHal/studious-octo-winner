using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class PauseMenu : MonoBehaviour
{
    public GameObject Canvas;
    bool Paused = false;
    bool Active = false;
    // Start is called before the first frame update
    void Start()
    {
        Canvas.gameObject.SetActive(false); //hides pause menu on start
        Time.timeScale = 1.0f; //makes sure time flows after restart/going to menu and starting
    }

 
    void Update() //opens pause menu on esc
    {
        if (Input.GetKeyDown("escape"))
        {
            if (Paused == true)
            {
                Time.timeScale = 1.0f;
                Canvas.gameObject.SetActive(false);
                Paused = false;
            }
            else
            {
                Time.timeScale = 0.0f;
                Canvas.gameObject.SetActive(true);
                Paused = true;
            }
        }
    }
    public void Resume()
    {
        Time.timeScale = 1.0f;
        Canvas.gameObject.SetActive(false);
    }
    public void ReturnTomenu(int sceneNumber)
    {
        SceneManager.LoadScene(sceneNumber);
    }
    public void Restart(int sceneNumber)
    {
        SceneManager.LoadScene(sceneNumber);
        Time.timeScale = 1.0f;
    }
}
