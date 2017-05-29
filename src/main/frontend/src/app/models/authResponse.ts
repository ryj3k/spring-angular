import {Serializable} from './serializable.base';
export class AuthResponse extends Serializable{
  status: number;
  message: string;
  errorCode: number;
  timestamp: number;
}
