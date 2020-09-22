using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour
{
    public float thrustSpeed;
    public float turnSpeed;
    public float hoverPower;
    public float hoverHeight;
    public float jumpSpeed;
    public float FlySpeed;
    public Rigidbody shipRigidBody;
    private float thrustInput;
    private float turnInput;
 

    // Start is called before the first frame update
    void Start()
    {
        shipRigidBody = GetComponent<Rigidbody>();
        
    }

    // Update is called once per frame
    void Update()
    {
        thrustInput = Input.GetAxis("Vertical");
        turnInput = Input.GetAxis("Horizontal");


        if (Input.GetKey(KeyCode.UpArrow) || Input.GetKey(KeyCode.W))
            thrustInput = 1;
        else if (Input.GetKey(KeyCode.DownArrow) || Input.GetKey(KeyCode.S))
            thrustInput = -1;
        else
            thrustInput = 0;

        if (Input.GetKey(KeyCode.LeftArrow) || Input.GetKey(KeyCode.A))
            turnInput = -1;
        else if (Input.GetKey(KeyCode.RightArrow) || Input.GetKey(KeyCode.D))
            turnInput = 1;
        else
            turnInput = 0;
        //fly higher
        /*if (Input.GetKey(KeyCode.LeftControl))
        {
            shipRigidBody.AddForce(Vector3.up * FlySpeed);
        }*/

    }
    private void FixedUpdate()
    {
        //Turning the ship
        shipRigidBody.AddRelativeTorque(0F, turnInput * turnSpeed, 0f);

        //Moving the ship
        shipRigidBody.AddRelativeForce(0f, 0f, thrustInput * thrustSpeed);

        // Hovering
        Ray ray = new Ray(transform.position, -transform.up);
        Ray groundray = new Ray(transform.position, -transform.up);
        RaycastHit hit;
        RaycastHit hit2;
        if (Physics.Raycast(ray, out hit, hoverHeight))
        {
            float proportionalHeight = (hoverHeight - hit.distance) / hoverHeight;
            Vector3 appliedHoverForce = Vector3.up * proportionalHeight * hoverPower;
            shipRigidBody.AddForce(appliedHoverForce, ForceMode.Acceleration);
        }
        //jump
       /* if (Physics.Raycast(groundray, out hit2, hoverHeight))
        {
            if(Input.GetKey(KeyCode.Space))
            shipRigidBody.AddForce(Vector3.up * jumpSpeed);

        }*/
    }
}
