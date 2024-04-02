import { PostcodeDataPair } from "./interfaces.ts";

const API_URL = "http://localhost:8080/postcode";

export const addData = async (
  postcode: Number,
  suburb: string
): Promise<PostcodeDataPair> => {
  console.log(postcode, "postcode");
  console.log(suburb);
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ suburbName: suburb, postcodeNumber: postcode }),
    });

    if (!response.ok) {
      const errorMessage = `Failed to add item. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }

    return await response.json();
  } catch (error) {
    console.log(error);
    throw error;
  }
};

export const getAllData = async (): Promise<PostcodeDataPair[]> => {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) {
      const errorMessage = `Failed to get posts. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }
    const data: PostcodeDataPair[] = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.log(error);
    throw error;
    // this error ^ is the one that gets sent to the async function that calls it and catches the error
  }
};

export const getPostcodeOrSuburb = async (
  type: string,
  query: number | string
): Promise<string> => {
  try {
    let response: Response;

    if (type === "POSTCODE") {
      response = await fetch(`${API_URL}/number/${query}`);
    } else if (type === "SUBURB") {
      response = await fetch(`${API_URL}/name/${query}`);
    } else {
      throw new Error("Invalid type");
    }

    if (!response.ok) {
      const errorMessage = `Failed to get data. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }

    let data: string;

    if (type === "POSTCODE" || type === "SUBURB") {
      data = await response.text();
      console.log(data);
      return data;
    }
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// export const getPostcodeOrSuburb = async (
//   type: String,
//   query: Number | String
// ): Promise<String | Number> => {
//   try {
//     let response: any;
//     console.log(query);
//     if (type == "POSTCODE") {
//       response = await fetch(`${API_URL}/number/${query}`);
//     }
//     if (type == "SUBURB") {
//       response = await fetch(`${API_URL}/name/${query}`);
//     }

//     console.log(response);

//     if (!response.ok) {
//       const errorMessage = `Failed to get posts. Status: ${response.status} ${response.statusText}`;
//       console.error(errorMessage);
//       throw new Error(errorMessage);
//     }

//     let data: String | Number;

//     if (type === "POSTCODE") {
//       data = (await response.json()) as String;
//     } else if (type === "SUBURB") {
//       data = (await response.json()) as Number;
//     } else {
//       throw new Error("Invalid type");
//     }

//     console.log(data);
//     return data;
//   } catch (error) {
//     console.log(error);
//     throw error;
//     // this error ^ is the one that gets sent to the async function that calls it and catches the error
//   }
// };

export const deleteData = async (postcode: Number) => {
  try {
    const response = await fetch(`${API_URL}/${postcode}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      const errorMessage = `Failed to delete item. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }
  } catch (error) {
    console.log(error);
    throw error;
  }
};
